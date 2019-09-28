<template>
    <div>
        <h1>Создать событие</h1>
        <div style="width: 40%; margin: auto">
            <form @submit.prevent="onAdd" style="text-align: center">
                <div>
                    <label>
                        <input required class="form-control" id="title" name="title" placeholder="Название"
                               v-model="title"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input required class="form-control" id="description" name="description" placeholder="Описание"
                               v-model="description"/>
                    </label>
                </div>
                <div>
                    <label>Музей:
                        <select class="form-control" id="museum" v-model="museum">
                            <option disabled hidden selected value="">Музей</option>
                            <option :key="museum.id" :value="museum"
                                    v-for="museum in museums">
                                {{ museum.name }}
                            </option>
                        </select>
                    </label>
                </div>
                <div>
                    <label>Начало:
                        <input required class="form-control" id="startDate" name="startDate" type="datetime-local"
                               v-model="startDate"/>
                    </label>
                </div>
                <div>
                    <label>Конец:
                        <input required class="form-control" id="endDate" name="endDate" type="datetime-local"
                               v-model="endDate"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input required class="form-control" id="volunteersRequired" name="volunteersRequired"
                               placeholder="Требуется волонтёров" v-model="volunteersRequired"/>
                    </label>
                </div>
                <div>
                    <label>
                        <select class="form-control" id="type" v-model="type">
                            <option disabled hidden selected value="">Тип события</option>
                            <option :key="type.id" :value="type"
                                    v-for="type in types">
                                {{ type }}
                            </option>
                        </select>
                    </label>
                </div>
                <div>
                    <label>
                        <input required class="form-control" id="linkToEvent" name="linkToEvent"
                               placeholder="Ссылка на мероприятие" v-model="linkToEvent"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input required class="form-control" id="photo" name="photo" placeholder="Фото" v-model="photoLink"/>
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
        name: "Event",
        props: ['museums'],
        data() {
            return {
                title: '',
                description: '',
                museum: null,
                startDate: null,
                endDate: null,
                volunteersRequired: null,
                type: '',
                linkToEvent: '',
                photoLink: '',
                types: ['Праздник', 'Презентация', 'Выставка', 'Экскурсия']
            }
        },
        methods: {
            onAdd() {
                const event = {
                    title: this.title,
                    description: this.description,
                    museum: this.museum,
                    startDate: new Date(this.startDate).getTime(),
                    endDate: new Date(this.endDate).getTime(),
                    volunteersRequired: this.volunteersRequired,
                    volunteersPresent: 0,
                    finished: false,
                    type: this.type,
                    linkToEvent: this.linkToEvent,
                    photoLink: this.photoLink
                };
                axios.post('event', event).then(() => this.$root.$emit("onAddEvent", event))
                    .catch(e => this.error = e.response.data.message);
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