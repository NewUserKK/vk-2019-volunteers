<template>
    <div>
        <h1>Управление заявками</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Волонтёр</th>
                    <th class="table-head">Событие</th>
                    <th class="table-head">Роль</th>
                    <th class="table-head">Начало</th>
                    <th class="table-head">Конец</th>
                    <th class="table-head">Комментарий</th>
                    <th class="table-head">Действия</th>
                </tr>
                </thead>
                <tbody>
                <tr :key="request.id" v-for="request in requests">
                    <td>{{request.user && request.user.name + ' ' + request.user.surname}}</td>
                    <td>{{request.event && request.event.title}}</td>
                    <td>{{request.role && request.role.name}}</td>
                    <td>{{new Date(request.startDate).toLocaleString()}}</td>
                    <td>{{new Date(request.endDate).toLocaleString()}}</td>
                    <td>{{request.comment}}</td>
                    <td>
                        <router-link :to="`/events/${request.id}/manage`">Управление</router-link>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Requests",
        props: ['requests']
    }
</script>

<style scoped>
    h1 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>