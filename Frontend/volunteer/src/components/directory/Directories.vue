<template>
    <div>
        <h1>Справочники</h1>
        <div class="cards">
            <div class="card" @click="redirect('museums')">
                <img src="../../../public/img/museum.svg" alt="Avatar">
                <div class="container">
                    <h4><b>Музеи</b></h4>
                </div>
            </div>
            <div class="card" @click="redirect('events')">
                <img src="../../../public/img/event.svg" alt="Avatar">
                <div class="container">
                    <h4><b>События</b></h4>
                </div>
            </div>
            <div class="card" @click="redirect('volunteers')">
                <img src="../../../public/img/volunteer.svg" alt="Avatar">
                <div class="container">
                    <h4><b>Волонтёры</b></h4>
                </div>
            </div>
            <div class="card" @click="redirect('roles')">
                <img src="../../../public/img/role.svg" alt="Avatar">
                <div class="container">
                    <h4><b>Роли</b></h4>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Directories",
        methods: {
            redirect(path) {
                this.$router.push({path : path});
            }
        }
    }
</script>

<style scoped>
    .cards {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
    }
    h1, h4 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 2rem;
    }
    .card {
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.3);
        transition: 0.3s;
        border-radius: 5px;
        margin: 1rem;
        padding: 1rem;
        min-height: 300px;
        min-width: 250px;
        align-items: center;
    }
    .card:hover {
        box-shadow: 0 12px 20px 0 rgba(0,0,0,0.4);
    }
    .container {
        padding: 2px 16px;
    }
    img {
        height: 300px;
        width: 220px;
        border-radius: 5px 5px 0 0;
    }
</style>