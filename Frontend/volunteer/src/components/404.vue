<template>
    <div id="notfound">
        <div class="notfound">
            <div class="notfound-404">
                <h1>404</h1>
            </div>
            <h2>Страница не найдена!</h2>
            <p>Страница, которую вы запрашиваете, не существует, была удалена, переименована или временно
                недоступна</p>
            <router-link to="/">На домашнюю страницу</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: '404'
    }
</script>

<style scoped>
</style>