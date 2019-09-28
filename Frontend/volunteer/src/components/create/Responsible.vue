<template>
    <div>
        <h1>Создать представителя</h1>
        <div style="width: 40%; margin: auto">
            <form @submit.prevent="onAdd" style="text-align: center">
                <div>
                    <label>Событие:
                        <select class="form-control" id="event" v-model="event">
                            <option disabled hidden selected value="">Музей</option>
                            <option :key="event.id" :value="event"
                                    v-for="event in events">
                                {{ event.title }}
                            </option>
                        </select>
                    </label>
                </div>
                <div>
                    <label>Представитель:
                        <select class="form-control" id="responsible" v-model="responsible">
                            <option disabled hidden selected value="">Ответственный</option>
                            <option :key="responsible.id" :value="responsible"
                                    v-for="responsible in this.responsibles">
                                {{ responsible.surname + ' ' + responsible.name + ' ' + responsible.patronymic}}
                            </option>
                        </select>
                    </label>
                </div>
                <div class="error">{{error}}</div>
                <button type="submit" class="myButton">Добавить</button>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Museum",
        props: ['events'],
        data() {
            return {
                event: null,
                responsible: null,
                responsibles: []
            }
        },
        methods: {
            onAdd() {
                axios.put('event/' + this.event.id + '/addResponsible?userId=' + this.responsible.id)
                    .then(() => this.$root.$emit("onAddResponsible", this.event, this.responsible))
                    .catch(e => this.error = e.response.data.message);
            }
        },
        beforeCreate() {
            //Захардкоженный Куратор
            axios.get('user/byRole/' + 22).then(
                response => this.responsibles = response.data
            )
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
        font-family: Montserrat, serif;
        text-align: left;
    }

    button {
        margin-top: 1rem;
    }

    input, select {
        width: 400px;
        border-radius: 10px;
    }
</style>