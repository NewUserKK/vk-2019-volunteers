<template>
    <div>
        <h1>Волонтёры</h1>
        <div class="datatable">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Фамилия</th>
                    <th class="table-head">Имя</th>
                    <th class="table-head">Отчество</th>
                    <th class="table-head">Дата рождения</th>
                    <th class="table-head">Страничка в соц.сетях</th>
                    <th class="table-head">Опыт волонтёрства</th>
                    <th class="table-head">Аллергии, противопоказания</th>
                    <th class="table-head">Размер одежды</th>
                    <th class="table-head">Фото</th>
                    <th class="table-head">Почта</th>
                    <th class="table-head">Телефон</th>
                </tr>
                </thead>
                <tbody>
                <tr :key="volunteer.id" v-for="volunteer in volunteers">
                    <td>{{volunteer.surname}}</td>
                    <td>{{volunteer.name}}</td>
                    <td>{{volunteer.patronymic}}</td>
                    <td>{{new Date(volunteer.birthday).toLocaleDateString()}}</td>
                    <td>{{volunteer.socialLink}}</td>
                    <td>{{volunteer.hasVolunteeringExperience ? 'Да' : 'Нет'}}</td>
                    <td>{{volunteer.hasAllergies ? 'Да' : 'Нет'}}</td>
                    <td>{{volunteer.size}}</td>
                    <td>{{volunteer.photoLink}}</td>
                    <td>{{volunteer.email}}</td>
                    <td>{{volunteer.phone}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Volunteers',
        props: ['volunteers']
    }
</script>

<style scoped>
    h1 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>