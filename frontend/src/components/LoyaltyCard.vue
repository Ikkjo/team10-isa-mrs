<template>
    <div @click="$emit('click')" class="loyalty-card" :style="'background-color:'+color">
        <div class="card-padding-container">
            <div class="top-left">
                <p class="title">{{title}}</p>
            </div>
            <div class="center">
                View details
            </div>
            <div class="bottom-left">
                <p class="percentage">Client Discount: {{clientDiscount}}%</p>
                <p class="percentage">Business Client Cut: {{businessClientCut}}%</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'LoyaltyCard',
    props: {
        title: String,
        clientDiscount: Number,
        businessClientCut: Number,
        color: String
    },
}
</script>

<style scoped>
.loyalty-card:hover .center {
    text-decoration: underline;
}

.card-padding-container {
    width: 100%;
    height: 100%;
    padding: 20px;
    display: flex;
    flex-direction: column;
}
.top-left {
    flex-grow: 1;
}
.title {
    font-size: 1.8rem;
}
.center {
    flex-grow: 1;
    text-align: center;
}
.bottom-left {
    font-size: 1.4rem;
    flex-shrink: 0;
    flex-grow: 0;
    position: relative;
    bottom: 0;
    left: 0;
}
</style>