<template>
    <div class="wrapper">
        <UniversalNavBar/>
        <div class="not-found">
            <h1>404 - Page not found</h1>
            <router-link :to="{name: 'homepage'}" class="btn" tag="button">
                Return to homepage
            </router-link>
        </div>
    </div>
</template>

<script>
import UniversalNavBar from '@/components/UniversalNavBar.vue'

export default {
    name: 'NotFound404',
    components: {
        UniversalNavBar
    }
}
</script>

<style scoped>
.wrapper {
    display: flex;
    align-content: center;
    justify-content: center;
    height: 100%;
    width: 100%;
    background: linear-gradient(0deg, var(--orange-primary, orange)5%, whitesmoke );
}

.not-found {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.not-found h1 {
    font-size: 4rem;
    color: white;
    margin-bottom: 15px;
}

.not-found .btn {
    flex-grow: 0;
    align-self: center;
}
</style>