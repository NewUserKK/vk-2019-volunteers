<template>
    <div>
        <h1>Музеи</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Название</th>
                    <th class="table-head">Описание</th>
                    <th class="table-head">Город</th>
                    <th class="table-head">Адрес</th>
                    <th class="table-head">Часы работы</th>
                    <th class="table-head">Фотография</th>
                </tr>
                </thead>
                <tbody>
                <tr :key="museum.id" v-for="museum in museums">
                    <td>{{museum.name}}</td>
                    <td>{{museum.description}}</td>
                    <td>{{museum.city}}</td>
                    <td>{{museum.address}}</td>
                    <td>{{museum.hours}}</td>
                    <td>{{museum.photo}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Museums',
        props: ['museums']
    }
</script>

<style scoped>
    h1 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>