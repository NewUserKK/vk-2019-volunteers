<template>
    <div class="the-modal" role="dialog">
        <div class="the-modal__dialog" role="document">
            <section class="the-modal__content">
                <div class="the-modal__header">
                    <span class="the-modal__title">{{ header }}</span>
                    <button @click="close" class="the-modal__close" type="button">
                        <span>X</span>
                    </button>
                </div>
                <div class="the-modal__body">
                    <slot></slot>
                </div>
                <div class="the-modal__footer" v-if="hasFooterSlot">
                    <slot name="footer"></slot>
                </div>
            </section>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            /**
             * Header of modal
             */
            header: {type: String, default: null}
        },

        computed: {
            modalCount: {
                get() {
                    return this.$store.getters.modalCount;
                },

                set(value) {
                    this.$store.dispatch("setModalCount", value);
                }
            },

            hasFooterSlot() {
                return !!this.$slots.footer;
            }
        },

        created() {
            document.addEventListener("keyup", this.closeByEscape);

            this.modalCount += 1;
            this.current = this.modalCount;
            if (this.modalCount === 1) {
                const scrollWidth = window.innerWidth - document.body.clientWidth;
                document.body.style.overflowY = "hidden";
                document.body.style.paddingRight = `${scrollWidth}px`;
            }
        },

        beforeDestroy() {
            this.modalCount -= 1;
            document.removeEventListener("keyup", this.closeByEscape);

            if (this.modalCount === 0) {
                document.body.style.overflowY = "";
                document.body.style.paddingRight = "";
            }
        },

        methods: {
            close() {
                this.$emit("close");
            },

            closeByEscape(e) {
                if (this.isCurrent && e.keyCode === 27) this.close();
            }
        }
    };
</script>

<style lang="postcss">
    .the-modal {
        background: rgba(0, 0, 0, 0.4);
        display: flex;
        height: 100%;
        justify-content: center;
        left: 0;
        overflow-x: hidden;
        overflow-y: auto;
        padding: 10px;
        position: fixed;
        top: 0;
        width: 100%;
        z-index: 10000;

    &
    __dialog {
        max-height: 100%;
        display: flex;
        align-items: center;
    }

    &
    __content {
        max-height: 100%;
        position: relative;
        display: flex;
        flex-direction: column;
        max-width: 835px;
        background-color: white;
    }

    &
    __header {
        align-items: center;
        background-color: #F7F7F7;
        border-bottom: none;
        display: flex;
        height: 44px;
        padding: 0 10px;
        width: 100%;
    }

    &
    __title {
        padding-right: 5px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 16px;
    }

    &
    __body {
        padding: 10px 15px;
        flex-grow: 1;
        overflow-y: auto;

    &
    ::-webkit-scrollbar {
        width: 4px;
    }

    &
    ::-webkit-scrollbar-thumb {
        background-color: #E6E6E6;
        border-radius: 8px;
    }

    }

    &
    __footer {
        border-top: 1px solid #E6E6E6;
        padding: 10px;
    }

    &
    __close {
        margin-left: auto;
    }

    &
    __close {
        width: 44px;
        height: 44px;
        border-radius: 0;

    @media (min-width: 768px) {
        width:

    30px

    ;
        height:

    30px

    ;
        border:

    1px solid #E6E6E6

    ;
        border-radius:

    50%;
        background: #F7F7F7

    ;
    }

    }

    &
    __close-icon.ft-icon {
        color: #75787E;
    }

    }
</style>
