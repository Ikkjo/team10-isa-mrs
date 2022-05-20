<template>
    <div id="vacation-home-card">
        <img class="cover-photo" :src="vacationHome.pictures[0]" alt="" srcset="">
        <div class="container">
            <div class="main-text">{{vacationHome.title}}</div>
            <div class="desc">
                <div>{{vacationHome.address.address}} {{vacationHome.address.city}} {{vacationHome.address.country}}</div>
                <div>{{vacationHome.rooms}} rooms, {{vacationHome.beds}} beds</div>
            </div>
            <div class="main-text">${{vacationHome.price}} night</div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'VacationHomeCard',
    props: ['vacationHome']
}
</script>

<style scoped>
#vacation-home-card {
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