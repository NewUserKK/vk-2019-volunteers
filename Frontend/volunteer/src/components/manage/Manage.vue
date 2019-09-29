<template>
    <div>
        <h1>Управление</h1>
        <div class="cards">
            <div @click="redirect('events')" class="card">
                <img alt="Avatar" src="../../../public/img/event.svg">
                <div class="container">
                    <h4><b>События</b></h4>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Manage",
        methods: {
            redirect(path) {
                this.$router.push({path: path});
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
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3);
        transition: 0.3s;
        border-radius: 5px;
        margin: 1rem;
        padding: 1rem;
        min-height: 300px;
        min-width: 250px;
        align-items: center;
    }

    .card:hover {
        box-shadow: 0 12px 20px 0 rgba(0, 0, 0, 0.4);
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