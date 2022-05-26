<template>
    <div id="rental-entity-card" @click="detailedView()" >
        <img class="cover-photo" :src="rentalEntity.pictures[0]" alt="" srcset="">
        <div class="container">
            <div class="main-text">{{rentalEntity.title}}</div>
            <div class="desc">
                <div>{{rentalEntity.address.address}} {{rentalEntity.address.city}} {{rentalEntity.address.country}}</div>
                <div>{{rentalEntity.rooms}} rooms, {{rentalEntity.beds}} beds</div>
            </div>
            <div class="main-text">${{rentalEntity.price}} night</div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'RentalEntityCard',
    props: ['rentalEntity'],
    methods: {
        detailedView() {
            this.$router.push({
                name: 'my-listing',
                params: {
                    id: this.rentalEntity.id,
                }
            })
        }
    }
}
</script>

<style scoped>
#rental-entity-card {
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
    cursor: pointer;
    max-width: 100%;
}


.cover-photo {
    object-fit: cover;
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
    width: 100%;
    height: 250px;
    border-radius: 5px 5px 0px 0px;
}

.desc {
    color: grey;
    padding: 5px 0;
}

.desc :first-child {
    margin-bottom: 2px;
}

.container {
    padding: 6px 5px;
}


</style>