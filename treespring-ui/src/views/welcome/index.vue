<template>

  <div class="bg">
    <div class="header">
      <div class="logoimg"><img :src="logo"></div>
      <div class="title">新闻资讯中心</div>
      <div class="divlogin">
        <a  @click="toLogin()" v-if="!isLogin">登录</a>
        <a  @click="toLogin()" v-if="isLogin">后台</a>
        <!--<el-button type="primary" @click="toLogin()" v-if="!isLogin">登录<i class="el-icon-upload el-icon&#45;&#45;right"></i>
        </el-button>
        <el-button type="primary" @click="toLogin()" v-if="isLogin">后台<i class="el-icon-upload el-icon&#45;&#45;right"></i>
        </el-button>-->
      </div>
    </div>
    <div class="content">
      <el-menu
        :default-active="activeIndex"
        @select="handleSelect"
        class="el-menu-demo menu"
        mode="horizontal"
        background-color="#ffffff"
        text-color="#004370"
        active-text-color="#004370">

        <el-menu-item class="w200 textAlign" v-for="(item,index) in kind" :key="index" :index="(index+'')" v-if="newsItemList[index]  !== undefined &&newsItemList[index].length >0">
          {{item.dictLabel}}
        </el-menu-item>
      </el-menu>
      <!--最新消息-->

      <div class="newInfo">
        <!--<img :src="imgUrl" alt="" class="imgStyle"/>-->

        <div class="block">

          <el-carousel trigger="click" height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
              <el-image :src="imgUrl" alt="" class="imgStyle" fit="fill" />
            </el-carousel-item>
          </el-carousel>
        </div>

        <div class="newsItemInfo">
          <div>
            <img class="img-info " src="../../assets/image/welcome/new_info.png">
          </div>
          <ul>
            <li v-for="(item,index) in newsTopList" :key="index" @click="goNewsInfo(item)" >
              <span class="news-title hidden-txt">{{item.noticeTitle}}</span>
              <span class="news-time">{{item.createTime}}</span>
            </li>
          </ul>
        </div>
      </div>


      <div class="tabs" v-for="(item,index) in kind" :key="index" :id="(index+'')" >
        <div class=" tabs-title flex_row" v-if="newsItemList[index]  !== undefined &&newsItemList[index].length >0">
          <span class=" tabs-title1">{{item.dictLabel}}</span>
          <!--<span class="flex1 textRight ">更多 >></span>-->
        </div>

        <div class="tabs-content">
          <ul>
            <li v-for="(its,key) in newsItemList[index]" :key="key" class="flex_row borderB" @click="goNewsInfo(its)">
              <span class="news-title hidden-txt">{{its.noticeTitle}}</span>
              <span class="news-time">{{its.createTime}}</span>
            </li>
          </ul>
        </div>
      </div>

    </div>


    <div class="footer">
      <div class="footer-top">
        <div class="footer-line">
          <div class="flex_row">
            <div class="footer-line-f">友情链接</div>
            <div class="footer-right">
              <a href="https://www.toutiao.com/" target="_blank">今日头条</a>
              <a href="http://www.gov.cn/index.htm" target="_blank">国家新闻政府网</a>
              <a href="https://www.sina.com.cn/" target="_blank">新浪新闻</a>
            </div>
          </div>
        </div>
      </div>
      <div class="footer-bottom">

        <div>免责声明：本站所有信息均来源于互联网搜集，并不代表本站观点，本站不对其真实合法性负责。如有信息侵犯了您的权益，请告知，本站将立刻删除。</div>
       <div>Copyright@2020-2020 某某新闻网 版权所有 备案号：京ICP备xxxxxxxx号</div>

      </div>
    </div>
  </div>
</template>

<script>
  import {list, getNoticeType} from '../../http/home';

  export default {
    name: "",
    data() {
      return {
        activeIndex: '0',
        imgUrl: require('../../assets/image/slider/slider01.jpg'),
        logo: require('../../assets/image/welcome/newslogo.png'),
        newsTopList: [],
        kind: [],
        newsItemList: [],
        isLogin: false
      }
    },
    computed: {
      name() {
        return this.$store.getters.name
      }
    },
    created() {
      if (this.name) {
        this.isLogin = true
      }

      getNoticeType().then(data => {
        this.kind = data.data
      }).catch(err => {
        this.$message.error(err.message);
      });
      list().then(data => {
        let dataList = data.rows
        this.newsTopList = dataList.slice(0, 5)
        let kind = this.kind
        let newsItemList = []
        for (let index in kind) {
          let kindList = []
          for (let key in dataList) {
            if (kind[index].dictValue == dataList[key].noticeType) {
              kindList.push(dataList[key])
            }
          }
          newsItemList.push(kindList)
        }
        this.newsItemList = newsItemList
      }).catch(err => {
        this.$message.error(err.message);
      });
    },
    methods: {
      toLogin() {
        this.$router.push('/login')
      },
      goNewsInfo(item) {
        let kind = this.kind
        let map = kind.find(t => t.dictValue == item.noticeType)
        this.$router.push({path: "/newsinfo", query: {id: item.noticeId, kindType: map.dictLabel}})
      },
      handleSelect(key, keyPath) {
        console.log(key);
        let ele = document.getElementById(key);
        let height = ele.offsetTop;
        window.scrollTo(0, height)
      }
    }
  }
</script>

<style scoped>
  .bg {
    width: 100%;
    height: 100%;
    padding-left: 200px;
    padding-right: 200px;
    position: absolute;
  }
.block{

  width: 33%;
}
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .logoimg img {
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

  .content {
    width: 100%;

    flex: 1;

  }

  .menu {
    padding-right: 120px;
    padding-left: 120px;
  }

  .footer {
    background-color: #004370;
    height: 180px;
    padding: 10px 80px;
    color: #acacac;
  }

  .footer-top {
    display: flex;
    flex-direction: column;
    height: 80px;
  }

  .footer-line {
    background-color: #545F62;
    height: 1px;
    margin-top: 30px;
  }

  .footer-line-f {
    text-align: center;
    border-top: 2px solid #acacac;
    width: 80px;
    margin-left: 50px;
    padding-top: 20px;
    color: white;
  }



  .footer-right {
    display: flex;
    flex-direction: row;
   margin-left: 50px;

  }

  .footer-right a {
    margin-right: 20px;
    margin-top: 20px;
    padding-right: 20px;
    border-right: 1px solid #545F62;
  }
  .footer-right a:hover{
    color: #1c84c6;
  }


  .ft_title {
    font-size: 20px;
    color: white;
  }

  .footer-bottom {

    margin-top: 30px;
    text-align: center;
    font-size: 14px;
  }
  .footer-bottom div{
    margin-top: 10px;
  }

  .newInfo {
    display: flex;
    flex-direction: row;
   padding-top: 30px;
    padding-bottom: 30px;
  }

  .imgStyle {
    width:auto;
    height:100%;
    image-rendering: crisp-edges;
  }

  .newsItemInfo {
    flex: 1;
  }

  .newsItemInfo ul li {
    display: flex;
    flex-direction: row;
    border-bottom: 1px solid #acacac;
    height: 50px;
  }

  .newsItemInfo ul li:hover {
    color: #1c84c6;
  }

  .news-title {
    flex: 1;
    line-height: 50px;
  }

  .news-time {
    width: 200px;
    line-height: 50px;
  }

  .tabs {
    padding-right: 100px;
    padding-left: 100px;
    display: flex;
    flex-direction: column;
  }

  .tabs-title {
    width: 100%;
    padding-top: 20px;
    border-bottom: 3px solid #1c84c6;
  }

  .tabs-content ul li:hover {
    color: #1c84c6;
  }

  .tabs-title1 {
    width: 150px;
    height: 60px;
    background-color: #1c84c6;
    color: white;
    font-size: 20px;
    line-height: 60px;
    text-align: center;
    font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
    border-radius: 6px 6px 0 0;
  }

  .tabs-content {

  }

  .flex1 {
    flex: 1;
  }

  .w200 {
    width: 200px;
  }

  .textAlign {
    text-align: center;
    font-size: 20px;
  }

  .img-info {
    width: 300px;
    margin-left: 30px;
  }

  .flex_row {
    display: flex;
    flex-direction: row;
  }

  .borderB {
    border-bottom: 1px solid #acacac;
  }

  .textRight {
    text-align: right;
    line-height: 60px;
  }

  .gray {
    color: #acacac
  }
  .hidden-txt{
    display: -webkit-box;/*作为弹性伸缩盒子模型显示*/
    -webkit-line-clamp: 1; /*显示的行数；如果要设置2行加...则设置为2*/
    overflow: hidden; /*超出的文本隐藏*/
    text-overflow: ellipsis; /* 溢出用省略号*/
    -webkit-box-orient: vertical;/*伸缩盒子的子元素排列：从上到下*/
    padding-right: 50px;

  }


</style>
