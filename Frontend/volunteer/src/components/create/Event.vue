<template>
    <div>
        <h1>Создать событие</h1>
        <div style="width: 40%; margin: auto">
            <form @submit.prevent="onAdd" style="text-align: center">
                <div>
                    <label>
                        <input class="form-control" id="title" name="title" placeholder="Название" required
                               v-model="title"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="description" name="description" placeholder="Описание" required
                               v-model="description"/>
                    </label>
                </div>
                <div>
                    <label>Музей:
                        <select required class="form-control" id="museum" v-model="museum">
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
                        <input class="form-control" id="startDate" name="startDate" required type="datetime-local"
                               v-model="startDate"/>
                    </label>
                </div>
                <div>
                    <label>Конец:
                        <input class="form-control" id="endDate" name="endDate" required type="datetime-local"
                               v-model="endDate"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="volunteersRequired" name="volunteersRequired"
                               placeholder="Требуется волонтёров"
                               required v-model="volunteersRequired"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="requiredRating" name="requiredRating"
                               placeholder="Минимальный рейтинг"
                               required v-model="requiredRating"/>
                    </label>
                </div>
                <div>
                    <label>Важность:
                        <select class="form-control" id="importanceTypes" v-model="importance">
                            <option disabled hidden selected value="">Музей</option>
                            <option :key="i" :value="i"
                                    v-for="(importance, i) in importanceTypes">
                                {{ importance }}
                            </option>
                        </select>
                    </label>
                </div>
                <div>
                    <label>
                        <select required class="form-control" id="type" v-model="type">
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
                        <input class="form-control" id="linkToEvent" name="linkToEvent"
                               placeholder="Ссылка на мероприятие"
                               required v-model="linkToEvent"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="photo" name="photo" placeholder="Фото" required
                               v-model="photoLink"/>
                    </label>
                </div>
                <label>Выберите роли:
                    <multiselect class="multiselect" v-model="selectedRoles" :options="roles" :multiple="true"
                                 :close-on-select="false"
                                 :clear-on-select="false" :preserve-search="true" placeholder="Pick some"
                                 label="name"
                                 track-by="name" :preselect-first="true">
                        <template slot="selection" slot-scope="{ values, search, isOpen }"><span
                                class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">Ролей выбрано: {{ values.length }}</span>
                        </template>
                    </multiselect>
                </label>
                <div class="error">{{error}}</div>
                <button class="myButton" type="submit">Добавить</button>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Event",
        props: ['museums', 'roles'],
        data() {
            return {
                title: '',
                description: '',
                museum: null,
                startDate: null,
                endDate: null,
                volunteersRequired: null,
                requiredRating: null,
                type: '',
                linkToEvent: '',
                photoLink: '',
                types: ['Праздник', 'Презентация', 'Выставка', 'Экскурсия'],
                importance: null,
                selectedRoles: [],
                importanceTypes: ['Незначительная', 'Низкая', 'Средняя', 'Значительная', 'Критическая']
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
                    requiredRating: this.requiredRating,
                    importance: this.importance === null ? 2 : this.importance,
                    finished: false,
                    type: this.type,
                    linkToEvent: this.linkToEvent,
                    photoLink: this.photoLink
                };
                axios.post('event', event).then(() => this.$root.$emit("onAddEvent", event, this.selectedRoles))
                    .catch(e => this.error = e.response.data.message);
            }
        }
    }
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

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

    input, select, .multiselect {
        width: 400px;
        border-radius: 10px;
    }
</style>