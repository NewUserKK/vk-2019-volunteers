<template>
    <div>
        <h1>Создать роль</h1>
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
                        <input class="form-control" id="description" name="description" placeholder="Описание"
                               required="required"
                               v-model="description"/>
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
        name: "Role",
        data() {
            return {
                name: '',
                description: ''
            }
        },
        methods: {
            onAdd() {
                const role = {
                    name: this.name,
                    description: this.description
                };
                axios.post('role', role).then(() => this.$root.$emit("onAddRole", role))
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