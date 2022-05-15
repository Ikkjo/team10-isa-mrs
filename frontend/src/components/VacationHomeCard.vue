<template>
    <div id="vacation-home-card">
        <img class="cover-photo" :src="vacationHome.image" alt="" srcset="">
        <div class="container">
            <div class="main-text">{{vacationHome.title}}</div>
            <div class="desc">
                <div>{{vacationHome.address}}</div>
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

<style>
#vacation-home-card {
    max-width: 270px;
    height: 350px;
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
    cursor: pointer;
}


.cover-photo {
    object-fit: cover;
    background-repeat: no-repeat;
    background-position: center; 
    max-width: 100%;
    min-height: 220px;
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