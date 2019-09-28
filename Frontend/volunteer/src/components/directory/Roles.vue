<template>
    <div>
        <h1>Роли</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Название</th>
                    <th class="table-head">Описание</th>
                    <th class="table-head">Пользователи</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="role in roles" :key="role.id">
                    <td>{{role.name}}</td>
                    <td>{{role.description}}</td>
                    <td>{{role.users && role.users.join(", ")}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Roles',
        props: ['roles']
    }
</script>

<style scoped>
    h1{
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>