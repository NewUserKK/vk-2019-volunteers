<template>
    <div style="width: 40%; margin: auto">
        <div style="text-align: center; font-family: 'Montserrat', sans-serif">
            <img src="../../../public/img/person.png" alt="Волонтёр" style="margin-bottom: 1.5rem; background-color: #731EC8; border-radius: 40px; padding: 20px">
            <h3 style="margin-bottom: 2rem">Вход</h3>
        </div>
        <form style="text-align: center">
            <div class="form-group">
                <label><input class="form-control" id="login" name="login" placeholder="Логин"
                              v-model="login"/></label>
            </div>
            <div class="form-group">
                <label>
                    <input class="form-control" id="password" name="password" placeholder="Пароль"
                           type="password"
                           v-model="password"/></label>
            </div>
            <div class="error">{{error}}</div>
            <a class="myButton" @click="onEnter">Войти</a>
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

    .myButton {
        background-color: transparent;
        -moz-border-radius: 28px;
        -webkit-border-radius: 28px;
        border-radius: 28px;
        border: 1px solid #18ab29;
        display: inline-block;
        cursor: pointer;
        color: #731ec8;
        font-family: Montserrat, serif;
        font-size: 17px;
        padding: 16px 31px;
        text-decoration: none;
        text-shadow: 0 1px 0 #2f6627;
    }

    .myButton:hover {
        background-color: transparent;
    }

    .myButton:active {
        position: relative;
        top: 1px;
    }
</style>
