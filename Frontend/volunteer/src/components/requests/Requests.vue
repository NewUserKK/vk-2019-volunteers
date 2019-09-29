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
                    <td>{{request.id}}</td>
                    <td>{{request.title}}</td>
                    <td>{{request.museum && request.museum.name}}</td>
                    <td>{{request.responsible ? request.responsible.name + ' ' + request.responsible.surname : ''}}</td>
                    <td>{{request.finished ? 'Да' : 'Нет'}}</td>
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
        props: {
            requests: Array
        }
    }
</script>

<style scoped>

</style>