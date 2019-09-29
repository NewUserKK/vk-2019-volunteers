<template>
    <div v-if="this.loaded">
        <h1>Событие №{{$route.params.id}}</h1>
        <h4>Выбраны роли:</h4>
        <div class="chosen">{{chosenRoles.length > 0 ? chosenRoles.map(r => r.name).join(", ") : "-"}}</div>
        <div class="roles">
            <div>
                <label>Добавьте роли:
                    <multiselect class="multiselect" v-model="selectedRoles" :options="availableRoles" :multiple="true"
                                 :close-on-select="false"
                                 :clear-on-select="false" :preserve-search="true" placeholder="Выберите"
                                 label="name"
                                 track-by="name">
                        <template slot="selection" slot-scope="{ values, search, isOpen }"><span
                                class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">Ролей выбрано: {{ values.length }}</span>
                        </template>
                    </multiselect>
                </label>
            </div>
            <a class="myButton" @click="add">Добавить</a>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Event",
        props: {
            roles: Array
        },
        data() {
            return {
                chosenRoles: [],
                availableRoles: [],
                selectedRoles: [],
                loaded: false,
            }
        },
        beforeMount() {
            this.getData()
        },
        methods: {
            getData() {
                this.loaded = false;
                axios.get(`role/byEvent/${this.$route.params.id}`).then(response => {
                    this.chosenRoles = response.data;
                    for (let role of this.roles) {
                        if (!this.chosenRoles.find(r => r.id === role.id)) {
                            this.availableRoles.push(role);
                        }
                    }
                    this.loaded = true;
                });
            },
            add() {
                const requests = [];
                const eventId = this.$route.params.id;
                for (let role of this.selectedRoles) {
                    requests.push(axios.post('role/' + eventId + '/add/' + role.id));
                }
                axios.all(requests).then(() => {
                    this.$router.push('/events');
                });
            }
        }
    }
</script>

<style scoped>
    h1 {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 2rem;
    }

    label {
        width: 400px;
    }

    h4, .chosen {
        font-family: Montserrat, serif;
        text-align: center;
        margin-bottom: 0.5rem;
    }

    .roles {
        min-height: 250px;
        text-align: center;
        font-family: Montserrat, serif;
    }
</style>