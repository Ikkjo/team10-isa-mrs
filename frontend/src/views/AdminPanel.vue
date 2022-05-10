<template>
    <div class="admin-panel-wrapper">
        <h1>Admin Panel</h1><hr>
        <div class="admin-panel">
            <router-link class="option" to="registration-requests">
                <div class="registration-requests">
                    <h2 class="title">Registration Requests</h2>
                    <p class="description">
                        List of registration requests from business clients. 
                        Accept valid registration requests.
                    </p>
                </div>
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    setup() {
        
    },
}
</script>

<style scoped>

h1 {
    padding: 20px;
}

.admin-panel {
    display: flex;
}

.option {
    margin: 20px;
}

.option .registration-requests {
    border-radius: 5px;
    border: 2px solid var(--orange-primary, orange);
    width: 300px;
    height: 150px;
    box-shadow:0 10px 16px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19) !important;
    transition: 0.5s;
}

.option .registration-requests:hover {
    border-color: var(--orange-secondary, darkorange);
}

.option .registration-requests h2 {
    margin-top: 10px;
    margin-left: 10px;
}

.option .registration-requests p {
    padding: 20px 10px;
}
</style>