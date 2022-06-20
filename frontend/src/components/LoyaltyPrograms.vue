<template>
    <div class="wrapper">
        <h1>Loyalty Programs</h1>
        <div class="loyalty-program-cards">
            <div v-if="cards" class="loyalty-cards-container">
                <LoyaltyCard v-for="card in cards"
                    :key="card.id"
                    :title="card.title"
                    :clientDiscount="card.clientDiscount"
                    :businessClientCut="card.businessClientCut"
                    :color="card.color"
                    @click="openLoyaltyProgram(card.id)"
                    />
                <div @click="addLoyaltyProgram" class="loyalty-card add-loyalty-card">
                    <span class="icon-class material-icons">add_box</span>
                    <div class="add-new">Add new loyalty program</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import LoyaltyCard from '@/components/LoyaltyCard.vue'
import axios from 'axios'

export default {
    name: 'LoyaltyPrograms',
    components: {
        LoyaltyCard,
    },
    data() {
        return {
            color: "null",
            cards: null,
        }
    },
    methods: {
        openLoyaltyProgram(id) {
            // this.$router.push({name: 'loyalty-program-details', params:{name: id}});
            console.log(id);
        },
        addLoyaltyProgram() {
            console.log("Add Loyalty Program clicked.")
        },
        updateColor(value) {
            this.color = value;
        },
        async animateCards() {
            await new Promise(r => setTimeout(r, 100));
            const tiltEffectSettings = {
                max: 25, // max tilt rotation (degrees (deg))
                perspective: 1000, // transform perspective, the lower the more extreme the tilt gets (pixels (px))
                scale: 1.1, // transform scale - 2 = 200%, 1.5 = 150%, etc..
                speed: 500, // speed (transition-duration) of the enter/exit transition (milliseconds (ms))
                easing: "cubic-bezier(.03,.98,.52,.99)" // easing (transition-timing-function) of the enter/exit transition
            };

            const cards = document.querySelectorAll(".loyalty-card");

            cards.forEach(card => {
                card.addEventListener("mouseenter", cardMouseEnter);
                card.addEventListener("mousemove", cardMouseMove);
                card.addEventListener("mouseleave", cardMouseLeave);
            });

            function cardMouseEnter(event) {
                setTransition(event);
            }

            function cardMouseMove(event) {
                const card = event.currentTarget;
                const cardWidth = card.offsetWidth;
                const cardHeight = card.offsetHeight;
                const centerX = card.offsetLeft + cardWidth/2;
                const centerY = card.offsetTop + cardHeight/2;
                const mouseX = event.clientX - centerX;
                const mouseY = event.clientY - centerY;
                const rotateXUncapped = (+1)*tiltEffectSettings.max*mouseY/(cardHeight/2);
                const rotateYUncapped = (-1)*tiltEffectSettings.max*mouseX/(cardWidth/2);
                const rotateX = rotateXUncapped < -tiltEffectSettings.max ? -tiltEffectSettings.max : 
                                (rotateXUncapped > tiltEffectSettings.max ? tiltEffectSettings.max : rotateXUncapped);
                const rotateY = rotateYUncapped < -tiltEffectSettings.max ? -tiltEffectSettings.max : 
                                (rotateYUncapped > tiltEffectSettings.max ? tiltEffectSettings.max : rotateYUncapped);

                card.style.transform = `perspective(${tiltEffectSettings.perspective}px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) 
                                        scale3d(${tiltEffectSettings.scale}, ${tiltEffectSettings.scale}, ${tiltEffectSettings.scale})`;
            }

            function cardMouseLeave(event) {
                event.currentTarget.style.transform = `perspective(${tiltEffectSettings.perspective}px) rotateX(0deg) rotateY(0deg) scale3d(1, 1, 1)`;
                setTransition(event);
            }

            function setTransition(event) {
                const card = event.currentTarget;
                clearTimeout(card.transitionTimeoutId);
                card.style.transition = `transform ${tiltEffectSettings.speed}ms ${tiltEffectSettings.easing}`;
                card.transitionTimeoutId = setTimeout(() => {
                    card.style.transition = "";
                }, tiltEffectSettings.speed);
            }
        }
    },
    created () {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/loyalty-programs',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        })
        .then((response) => {
            this.cards = response.data;
            this.animateCards();
        })
        .catch((error) => {
            alert("Couldn't fetch cards. See console for more info.")
            console.log(error);
        });
    },
}
</script>

<style scoped>

.loyalty-program-cards {
    justify-content: center;
    margin-top: 20px;
    display: flex;
    margin-bottom: 20px;
}

.loyalty-cards-container {
    max-width: 1000px;
    display: flex;
    align-content: flex-start;
    justify-content: flex-start;
    flex-wrap: wrap;
    gap: 50px;
}

.loyalty-card, .add-loyalty-card{
    cursor: pointer;
    flex-shrink: 0;
    flex-grow: 0;
    border-radius: 10px;
    width: 450px;
    height: 250px;
}

.loyalty-card {
    color: #fff;
}

.add-loyalty-card {
    font-size: 1.5rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: #fff;
    background-color: lightgray;
}

.icon-class {
    font-size: 3rem;
}

</style>