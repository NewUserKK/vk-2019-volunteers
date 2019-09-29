<template>
    <div style="width: 40%; margin: auto">
        <div style="text-align: center; font-family: 'Montserrat', sans-serif">
            <img alt="Волонтёр" src="../../../public/img/person.png"
                 style="margin-bottom: 1.5rem; background-color: #731EC8; border-radius: 40px; padding: 20px">
            <h3 style="margin-bottom: 2rem">Вход</h3>
        </div>
        <form style="text-align: center">
            <div class="form-group">
                <label>
                    <input class="form-control" id="login" name="login" placeholder="Логин" required
                           v-model="login"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    <input class="form-control" id="password" name="password" placeholder="Пароль" required
                           type="password"
                           v-model="password"/>
                </label>
            </div>
            <div class="error">{{error}}</div>
            <a @click="onEnter" class="myButton">Войти</a>
        </form>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data: function () {
            return {
                login: '',
                password: '',
                error: ''
            }
        },
        name: 'Enter',
        beforeMount() {
            this.login = '';
            this.password = '';
            this.error = '';
        }, methods: {
            onEnter: function () {
                this.error = '';
                axios.post('user/auth', {
                    login: this.login,
                    password: this.password
                }).then(response => {
                    this.$root.$emit('onEnter', response.data);
                    this.$router.push({path: '/'});
                }).catch(error => {
                    if (error.response.status === 403) {
                        this.error = 'Неверный логин или пароль.'
                    } else {
                        this.error = 'Ошибка сервера.'
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .error {
        font-family: Montserrat, serif;
        padding-bottom: 1em;
        color: var(--error-color);
    }
</style>
