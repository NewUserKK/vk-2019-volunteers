<template>
    <div>
        <h1>Роли</h1>
        <div class="datatable" v-if="this.loaded">
            <table>
                <thead>
                <tr>
                    <th class="table-head">Название</th>
                    <th class="table-head">Описание</th>
                    <th class="table-head">Пользователи</th>
                </tr>
                </thead>
                <tbody>
                <tr :key="role.id" v-for="role in roles">
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
    import axios from "axios";

    export default {
        name: 'Roles',
        data() {
            return {
                roles: [],
                loaded: false
            }
        },
        beforeMount() {
            this.getData();
        },
        methods: {
            getData() {
                this.loaded = false;
                axios.get('role').then(response => {
                    const requests = [];
                    this.roles = response.data;
                    for (let role of this.roles) {
                        requests.push(axios.get('user/byRole/' + role.id).then(
                            response => role.users = response.data.map(u => u.name + ' ' + u.surname)
                        ))
                    }
                    axios.all(requests).then(() => this.loaded = true);
                })
            }
        }
    }
</script>

<style scoped>
    h1 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 3rem;
    }
</style>