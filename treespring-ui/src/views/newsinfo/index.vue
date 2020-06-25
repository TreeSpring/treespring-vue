<template>
  <div class="info">
    <div class="header">
      <div class="logoimg"> <img :src="logo"></div>
      <div class="title">新闻资讯中心</div>
     <!-- <div class="divlogin">
        <el-button type="primary" @click="toLogin()" v-if="!isLogin">登录<i class="el-icon-upload el-icon&#45;&#45;right"></i>
        </el-button>
        <el-button type="primary" @click="toLogin()" v-if="isLogin">后台<i class="el-icon-upload el-icon&#45;&#45;right"></i>
        </el-button>
      </div>-->
    </div>
    <div class="bread">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/welcome' }" >{{kindType}}</el-breadcrumb-item>
        <el-breadcrumb-item>{{newsInfo.noticeTitle}}</el-breadcrumb-item>

      </el-breadcrumb>
    </div>
    <div class="details">

      <div class="de_title">{{newsInfo.noticeTitle}}</div>
      <div class="de_ctitle">
        <span>{{newsInfo.createTime}}</span>
        <span>作者：{{newsInfo.createBy}}</span>
      </div>
      <div v-html="newsInfo.noticeContent" style="width: 80%;margin-top: 40px" class="content_class"></div>
    </div>

  </div>

</template>

<script>
  import {getInfo,getImage} from '../../http/home';
    export default {
      data() {
        return {
          imgUrl: require('../../assets/image/slider/slider01.jpg'),
          logo: require('../../assets/image/welcome/newslogo.png'),
          kindType:"",
          newsInfo:{},
          id:"",
          isLogin:false
        }

      },
      created(){
        this.id = this.$route.query.id
        this.kindType =  this.$route.query.kindType
        if (this.name) {
          this.isLogin = true
        }
        this.getNewsInfo();

      },
      methods:{
        toLogin() {
          this.$router.push('/')
        },
        getNewsInfo(){
          getInfo(this.id).then(data=>{
            this.newsInfo = data.data
            let content = this.newsInfo.noticeContent
            content = getImage(content)
            this.newsInfo.noticeContent =  content
          }).catch(err => {
            this.$message.error(err.message);
          });
        }
      }
    }
</script>

<style scoped>
  .info{
    width: 100%;
    height: 100%;
    padding-left: 200px;
    padding-right: 200px;

  }
  .logoimg img{
    height: 100px;
    padding-right: 20px;
    padding-left: 120px;
  }
  .header {
    background-color: #004370;
    height: 100px;
    color: white;
    display: flex;
    flex-direction: row;

  }
  .content_class img {
    text-align: center;
  }
  .title {
    height: 100%;
    align-items: center;
    line-height: 100px;
    font-size: 30px;
    flex: 1;
    font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;

  }

  .divlogin {
    height: 100%;
    align-items: center;
    line-height: 100px;
    width: 200px;

  }

  .divlogin span {
    border: 1px solid #1ab394;
    padding: 20px;

  }
  /*詳情*/
  .details{
    display: flex;
    flex-direction: column;
    align-items: center;
   padding: 80px 50px ;
  }
  .de_title{
    font-size: 35px;
    text-align: center;
  }
  .de_ctitle{
    display: flex;
    flex-direction: row;
    width: 85%;
  }
  .de_ctitle span{
    font-size: 18px;
    margin-right: 20px;
    margin-left: 30px;
    color: #acacac;
    margin-top: 30px;
  }
  .bread{
    margin-top: 20px;
    font-size: 18px;
    color: #333333;
  }
</style>
