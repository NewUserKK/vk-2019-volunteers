<template>
    <div>
        <h1>Событие №{{$route.params.id}}</h1>
        <h4>Участники:</h4>
        <div class="chosen">{{participants.length > 0 ? participants.map(u => u.name + ' ' + u.surname).join(", ") : "-"}}</div>
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
            <h4 v-if="this.loaded && !this.event.finished">Вы можете <a href="javascript:void(0)" @click="finish">завершить</a> событие</h4>
            <h4 v-if="this.loaded && this.event.volunteersPresent < this.event.volunteersRequired">Вы так же можете <a href="javascript:void(0)" @click="distribute">дораспределить</a> волонтёров на событие</h4>
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
                participants: [],
                loaded: false,
                event: null,
            }
        },
        beforeMount() {
            this.getData()
        },
        methods: {
            getData() {
                this.loaded = false;
                axios.get(`event/${this.$route.params.id}`).then(response => {
                    this.event = response.data;
                    axios.get(`user/${this.$route.params.id}/participants`).then(response => {
                        this.participants = response.data;
                    });
                    axios.get(`role/byEvent/${this.$route.params.id}`).then(response => {
                        this.chosenRoles = response.data;
                        for (let role of this.roles) {
                            if (!this.chosenRoles.find(r => r.id === role.id)) {
                                this.availableRoles.push(role);
                            }
                        }
                        this.loaded = true;
                    });
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
            },
            finish() {
                axios.put(`event/${this.$route.params.id}/finish`).then(() => {
                    this.$root.$emit('onFinishEvent', this.$route.params.id);
                });
            },
            distribute() {
                axios.get(`user/${this.$route.params.id}/distribution`).then(response => {
                    for (let user of response.data) {
                        this.participants.push(user);
                    }
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
        margin-top: 0.5rem;
    }

    .roles {
        min-height: 250px;
        text-align: center;
        font-family: Montserrat, serif;
    }
</style>