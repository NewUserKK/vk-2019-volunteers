<template>
    <div></div>
</template>

<script>
    export default {
        name: "Logout",
        mounted() {
            this.$root.$emit('onLogout');
            this.$router.push({path: '/'});
        }
    }
</script>

<style scoped>

</style>