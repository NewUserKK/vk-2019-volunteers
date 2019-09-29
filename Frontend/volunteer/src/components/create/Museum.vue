<template>
    <div>
        <h1>Создать музей</h1>
        <div style="width: 40%; margin: auto">
            <form @submit.prevent="onAdd" style="text-align: center">
                <div>
                    <label>
                        <input class="form-control" id="name" name="name" placeholder="Название" required
                               v-model="name"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="description" name="description" placeholder="Описание" required
                               v-model="description"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="city" name="city" placeholder="Город" required
                               v-model="city"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="address" name="address" placeholder="Адрес" required
                               v-model="address"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="hours" name="hours" placeholder="Часы работы" required
                               v-model="hours"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input class="form-control" id="photo" name="photo" placeholder="Фото" required
                               v-model="photo"/>
                    </label>
                </div>
                <div class="error">{{error}}</div>
                <button class="myButton" type="submit">Добавить</button>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Museum",
        data() {
            return {
                name: '',
                description: '',
                city: '',
                address: '',
                hours: '',
                photo: ''
            }
        },
        methods: {
            onAdd() {
                const museum = {
                    name: this.name,
                    description: this.description,
                    city: this.city,
                    address: this.address,
                    hours: this.hours,
                    photo: this.photo
                };
                axios.post('museum', museum).then(() => this.$root.$emit("onAddMuseum", museum))
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