<template>
    <div>
        <h1>События</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Название</th>
                    <th class="table-head">Описание</th>
                    <th class="table-head">Музей</th>
                    <th class="table-head">Ответственный</th>
                    <th class="table-head">Начало</th>
                    <th class="table-head">Конец</th>
                    <th class="table-head">Тип</th>
                    <th class="table-head">Ссылка</th>
                    <th class="table-head">Фотография</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="event in events" :key="event.id">
                    <td>{{event.title}}</td>
                    <td>{{event.description}}</td>
                    <td>{{event.museum && event.museum.name}}</td>
                    <td>{{event.responsible ? event.responsible.name + ' ' + event.responsible.surname : ''}}</td>
                    <td>{{new Date(event.startDate).toLocaleString()}}</td>
                    <td>{{new Date(event.endDate).toLocaleString()}}</td>
                    <td>{{event.type}}</td>
                    <td>{{event.linkToEvent}}</td>
                    <td>{{event.photoLink}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Events',
        props: ['events']
    }
</script>

<style scoped>
    h1{
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>