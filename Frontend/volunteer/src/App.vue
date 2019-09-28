<template>
    <div>
        <header class='the-header'>
            <router-link to="/"><img alt="Волонтёр" src="../public/img/logo.png"
                                     style="margin: 2rem" title="Волонтёр"/></router-link>
            <div class="navigation">
                <ul id="vertical-navigation">
                    <li v-if="this.$user">
                        <router-link to="/create">Создать</router-link>
                    </li>
                    <li v-if="this.$user">
                        <router-link to="/directories">Справочники</router-link>
                    </li>
                    <li v-if="this.$user">
                        <router-link to="/logout">Выйти</router-link>
                    </li>
                    <li v-if="!this.$user">
                        <router-link to="/enter">Войти</router-link>
                    </li>
                </ul>
            </div>
        </header>
        <router-view :museums="museums" :events="events" :volunteers="volunteers"
                     :users="users" :roles="roles" class="middle"></router-view>
        <Footer/>
    </div>
</template>

<script>
    import Footer from './components/Footer'
    import axios from 'axios'

    axios.defaults.baseURL = '/api/v1/';

    export default {
        name: 'app',
        data: function () {
            return {
                museums: {},
                events: {},
                users: {},
                volunteers: {},
                roles: {}
            }
        },
        components: {
            Footer
        },
        created() {
            this.getData()
        },
        methods: {
            getData() {
                axios.get('museum').then(response => {
                    this.museums = response.data
                });
                axios.get('event').then(response => {
                    this.events = response.data
                });
                axios.get('user').then(response => {
                    this.users = response.data
                });
                axios.get('volunteer').then(response => {
                    this.volunteers = response.data
                });
                axios.get('role').then(response => {
                    this.roles = response.data;
                });
            }
        },
        beforeCreate() {
            this.$root.$on('onEnter', user => {
                this.$user = user;
                localStorage.setItem('user', user);
            });
            this.$root.$on('onLogout', () => {
                this.$user = null;
                localStorage.removeItem('user');
            });
            this.$root.$on('onAddMuseum', museum => {
                this.roles.push(museum);
                this.$router.push('/museums');
            });
            this.$root.$on('onAddEvent', event => {
                this.roles.push(event);
                this.$router.push('/events');
            });
            this.$root.$on('onAddRole', role => {
                this.roles.push(role);
                this.$router.push('/roles');
            });
            this.$root.$on('onAddResponsible', (event, responsible) => {
                this.events.forEach(v => {
                    if (v.id === event.id) {
                        v.responsible = responsible;
                    }
                });
                this.$router.push('/events');
            });
        },
        mounted() {
            this.$user = localStorage.getItem('user');
        }
    }
</script>
