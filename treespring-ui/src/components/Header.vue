<template>
    <header :style="{backgroundColor: bg_color, position: hd_position}" class="headerBg">
        <div class="headerstyle">
            <div :class="{img_hover: hover_flag}" class="web_icon" style="align-items: center;height: 100%">
                <!--<img :src="logoImg" alt="官网" @click="toHome">-->
              新闻资讯中心
            </div>
            <ul>
                <li v-for="(item,index) in titleList" :key="index" @click="selected(item.title)">
                    <router-link :to="item.link" :class="{active: active == item.title}">{{ item.title }}</router-link>
                </li>
            </ul>
        </div>
    </header>
</template>

<script>
export default {
    name: "OutLayoutHeader",
    props:{
        bg_color:{
            type: String,
            default: 'transparent'
        },
        hd_position:{
            type: String,
            default: 'fixed'
        },
        hover_flag:{
            type: Boolean,
            default: true
        }
    },
    data() {
        return {
            active: '首页',
            logoImg:require('../assets/image/brand-logo.png'),
            titleList:[
                {
                    title: '首页',
                    link: '/welcome'
                },
                {
                    title: '登录',
                    link: '/'
                },
            ]

        }
    },
    mounted(){
        this.active = this.$route.meta.nav;
    },
    methods: {
        selected(title){
            this.active = title;
        },
        toHome(){
            this.$router.push('/welcome');
            this.active = '首页';
        }
    }
};
</script>

<style lang="scss" scoped>
    $white: #ffffff;
    @mixin rim {
        background: transparent none repeat scroll 0 0;
        content: "";
        height: 10px;
        position: absolute;
        width: 25px;
        visibility: hidden;
        transition: .3s ease;
        opacity: 0;
    }
    header {
        color: $white;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        padding: 2% 0;
        transition: background-color 1.5s;
        &>div {
            display: flex;
            justify-content: space-between;
            margin: 0 10%;
            .img_hover:hover {
                &::before {
                    content: "";
                    height: 555px;
                    width: 100%;
                    position: absolute;
                    left: 0%;
                    top: 0;
                    background-color: transparent;
                    background-image: -webkit-linear-gradient(-30deg, transparent 30%, #fff 50%, transparent 70%); /*设置渐变的背景，按对角线渐变*/
                    background-blend-mode: hard-light; /*设置背景为混合模式下的强光模式*/
                    background-size: 200%;
                    animation: shine 5s forwards; /*给背景添加动画改变位置*/
                }
            }
            .web_icon {
                /*img {*/
                    /*position: absolute;*/
                    /*z-index: inherit;*/
                    /*cursor: pointer;*/
                /*}*/
              font-size: 30px;
              color: white;
              align-items: center;
            }
            ul {
                font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
                font-weight: bold;
                display: flex;
                list-style: none;
                li {
                    margin-right: 5px;
                    box-sizing: border-box;
                    position: relative;
                    a {
                        display: block;
                        line-height: 20px;
                        padding: 6px 15px;
                        text-decoration:none;
                        box-sizing: border-box;
                        color: $white;
                        position: relative;
                        background-color: transparent;
                        text-transform: uppercase;
                        transition: .3s ease;
                        &.active {
                            color: #ff8724;
                            &::before {
                                visibility: visible;
                                opacity: 1;
                                left: 0;
                            }
                            &::after {
                                visibility: visible;
                                opacity: 1;
                                right: 0;
                            }
                        }
                        &:hover {
                            background: none;
                            color: #ff8724;
                            outline: none;
                            &::before {
                                visibility: visible;
                                opacity: 1;
                                left: 0;
                            }
                            &::after {
                                visibility: visible;
                                opacity: 1;
                                right: 0;
                            }
                        }
                        &::before{
                            @include rim;
                            border-left: 1px solid #FF8724;
                            border-top: 1px solid #FF8724;
                            top: 0;
                            left: -70px;
                        }
                        &::after {
                            @include rim;
                            border-right: 1px solid #FF8724;
                            border-bottom: 1px solid #FF8724;
                            bottom: 0;
                            right: 70px;
                        }
                    }
                }
            }
        }
    }
// 动画
@keyframes shine {
    from {background-position: 130%;}
    to {background-position: -50%;}
}
  .headerBg{
    background-color: rgba(3,3,3,.8);
  }
  .headerstyle{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
</style>
