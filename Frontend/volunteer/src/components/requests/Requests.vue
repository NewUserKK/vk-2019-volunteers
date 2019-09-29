<template>
    <div>
        <h1>Управление заявками</h1>
        <div class="datatable" v-if="requests.length > 0">
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
                        <i class="fa fa-thumbs-up" aria-hidden="true" @click="accept(request)"></i>
                        <i class="fa fa-thumbs-down" aria-hidden="true" @click="decline(request)"></i>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <h4 v-else>Новых заявок нет</h4>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Requests",
        props: ['requests'],
        methods: {
            accept(request) {
                axios.post('request/accept', request).catch(e => alert(e.response.data.message));
                this.requests = this.requests.filter(r => r.id !== request.id);
            },
            decline(request) {
                axios.post('request/decline', request).catch(e => alert(e.response.data.message));
                this.requests = this.requests.filter(r => r.id !== request.id);
            }
        }
    }
</script>

<style scoped>
    h1, h4 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
    i {
        margin-right: 1rem;
        margin-left: 1rem;
    }
</style>