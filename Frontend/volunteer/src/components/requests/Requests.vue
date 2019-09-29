<template>
    <div>
        <h1>Управление заявками</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">ID</th>
                    <th class="table-head">Название</th>
                    <th class="table-head">Музей</th>
                    <th class="table-head">Ответственный</th>
                    <th class="table-head">Завершено</th>
                    <th class="table-head">Действия</th>
                </tr>
                </thead>
                <tbody>
                <tr :key="event.id" v-for="event in events">
                    <td>{{event.id}}</td>
                    <td>{{event.title}}</td>
                    <td>{{event.museum && event.museum.name}}</td>
                    <td>{{event.responsible ? event.responsible.name + ' ' + event.responsible.surname : ''}}</td>
                    <td>{{event.finished ? 'Да' : 'Нет'}}</td>
                    <td>
                        <router-link :to="`/events/${event.id}/manage`">Управление</router-link>
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