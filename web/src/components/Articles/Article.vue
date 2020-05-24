<template>
  <div>
  <SpaceHead></SpaceHead>
  <el-container>
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <div class="author-box">
        <div class="row-1">
          <div style="float: left">
            <el-avatar v-if="avatar" :src="require('../../assets/'+avatar+'.JPG')"></el-avatar>
            <el-avatar v-else icon="el-icon-user-solid"></el-avatar>
          </div>
          <a>{{article.username}}</a>
        </div>
        <div class="row-2">
          <span>粉丝数：{{numOfFans}}</span><br>
        </div>
        <el-button size="small" @click="cancelFocus" v-if="loadInfo.focus">已关注</el-button>
        <el-button size="small" @click="getFocus" v-else="loadInfo.focus">关注</el-button>
      </div>
    </el-aside>
    <el-container>
      <el-header>
          <h1>{{article.title}}</h1>
          <div class="info">
            <span>{{article.publishDate}}</span>
            <span>{{article.love}} 点赞</span>
            <span>{{article.forward}} 转发</span>
            <span>{{article.comment}} 评论</span>
            <span>{{article.collection}} 收藏</span>
          </div>
      </el-header>
    <el-main style="background-color: rgb(238, 241, 246)">
      <el-image :src="require('../../assets/'+article.cover+'.JPG')"
                style="width: 100px; height: 100px;float: left"
                fit="contain">
      </el-image>
      <div v-html="article.content"></div>
    </el-main>
      <el-footer>
        <el-row>
          <el-button type="success" size="mini" icon="el-icon-thumb" round @click="thumbUping" v-if="loadInfo.thumbUp">
            {{article.love}} 已点赞
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-thumb" round @click="thumbUping" v-else="loadInfo.thumbUp">
            {{article.love}} 点赞
          </el-button>
          <el-button type="success" size="mini" icon="el-icon-link" round @click="forwarding" v-if="loadInfo.forward">
            {{article.forward}} 已转发
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-link" round @click="forwarding" v-else="loadInfo.forward">
            {{article.forward}} 转发
          </el-button>
          <el-button type="success" size="mini" icon="el-icon-collection" round @click="collecting" v-if="loadInfo.collect">
            {{article.collection}} 已收藏
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-collection" round @click="collecting" v-else="loadInfo.collect">
            {{article.collection}} 收藏
          </el-button>
        </el-row>
      </el-footer>
      <el-main>
        <p>评论区</p>
        <el-form :inline="true" :model="comment" :rules="rules" ref="comment">
          <el-form-item label="我的评论" prop="content">
            <el-input type="textarea" :rows="2" v-model="comment.content">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" @click="addFirstComment('comment')">发表评论</el-button>
          </el-form-item>
        </el-form>
        <div v-for="(item,i) in first_comment" :key="i" style="position: relative;overflow:hidden;">
          <div style="float:left;width: 100px;top: 0;left: 0;bottom: 0;">
            <el-avatar :src="require('../../assets/'+commenter[i].picture+'.JPG')"></el-avatar>
            <span style="color: #9b9b9b;margin-left: 0">{{item.username}}</span>
          </div>
          <div>
            <p>{{item.content}}
              <el-dropdown placement="bottom-start">
                <span><i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="inform(i)">举报</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </p>
            <span>{{item.commentdate}}</span>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
    <el-dialog title="选择收藏夹" :visible.sync="dialogVisible" width="30%">
      <el-form ref="targetCollection" :model="targetCollection">
        <el-form-item label="选择收藏夹" prop="targetCollection">
          <el-select v-model="targetCollection.list_id">
            <el-option v-for="(collection,m) in collections"
                       :label="collection.name"
                       :key="m"
                       :value="collection.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitCollection('targetCollection')">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="关注" :visible.sync="dialogVisible1" width="30%">
      <el-form ref="focus" :model="focus" :rules="rules">
        <el-form-item label="选择分组" prop="groupsId">
          <el-select v-model="focus.groupsId">
            <el-option v-for="(group,m) in groups"
                       :label="group.name"
                       :key="m"
                       :value="group.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submitGroup('focus')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios'
  import SpaceHead from '../Head/SpaceHead'
  import {makeDate} from "../../assets/dateformat";

  export default {
  name: 'Article',
  components: {SpaceHead},
  data(){
    return {
      /*稿件基本信息*/
      article:{
        id: '',
        title: '',
        content: '',
        cover:'',
        publishDate:'',
        love:'',
        forward:'',
        comment:'',
        collection:'',
        userId:'',
        username:'',
      },
      /*作者头像*/
      avatar:'',
      /*用户评论*/
      comment:{
        manuscriptId:'',
        userId:'',
        username:'',
        content:'',
        love:'',
        commentdate:'',
      },

      /*作者粉丝数*/
      numOfFans:'',

      /*用户是否点赞、收藏、评论*/
      loadInfo:{
        thumbUp:'',
        forward:'',
        collect:'',
        focus:'',
      },

      first_comment:[],
      commenter:[],
      dialogVisible:false,
      dialogVisible1:false,
      /*目标收藏夹*/
      targetCollection:{
        manuscript_id:'',
        user_id:'',
        list_id:'',
      },
      /*关注相关信息*/
      focus:{
        userId:'',
        friendId:'',
        friendName:'',
        beginTime:'',
        remarks:'',
        groupsId:'',
      },
      /*用户全部好友分组*/
      groups:[],
      /*用户全部收藏夹*/
      collections:[],
      rules:{
        content:[
          { required: true, message: '请输入评论内容', trigger: 'blur' },
        ],
        targetCollection:[
          { required: true, message: '请选择收藏夹', trigger: 'change' },
        ],
        groupsId:[
          { required: true, message: '请选择关注分组', trigger: 'change' },
        ]
      },
    }
  },
  mounted () {
    this.article.id=this.$route.params.id
    let url1 = `http://localhost:8088/manuscript/selById/${this.article.id}`
    /*加载稿件数据*/
    axios.get(url1).then((res)=>{
      this.article = res.data
      console.log(this.article)
      let url2 = `http://localhost:8088/friend/getFansNumber`
      /*加载作者粉丝数*/
      axios.get(url2,{
        params:{
          user_id:this.article.userId
        }
      }).then((res)=>{
        this.numOfFans=res.data
      })
      let tmp ={
        userId: JSON.parse(sessionStorage.getItem('form')).id,
        manuscriptId:this.article.id
      }

      let url3 = `http://localhost:8088/loveData/selByUser`
      axios.post(url3,tmp).then(res=>{
        this.loadInfo.thumbUp=res.data.success
      })
      let url4 = `http://localhost:8088/forwardData/selByUser`
      axios.post(url4,tmp).then(res=>{
        this.loadInfo.forward=res.data.success
      })
      let url5 = `http://localhost:8088/collectionData/selByUser`
      axios.post(url5,tmp).then(res=>{
        this.loadInfo.collect=res.data.success
      })
      /*判断用户有无关注作者*/
      let url7 =`http://localhost:8088/friend/isFriend`
      axios.get(url7,{
        params:{
          user_id:JSON.parse(sessionStorage.getItem('form')).id,
          friend_id:this.article.userId,
        }
      }).then(res=>{
        this.loadInfo.focus=res.data
      })

      /*加载文章评论信息*/
      let url6=`http://localhost:8088/firstComment/selAll/${this.article.id}`
      axios.get(url6).then(res=>{
        this.first_comment=res.data
        for(let i=0;i<this.first_comment.length;i++){
          let url=`http://localhost:8088/user/selById/${this.first_comment[i].userId}`
          axios.get(url).then(res=>{
            console.log(res)
            this.commenter.push(res.data)
          })
        }
      })

      let url8 =`http://localhost:8088/user/selById/${this.article.userId}`
      axios.get(url8).then(res=>{
        if(res.data.picture!=null){
          this.avatar=res.data.picture
        }
      })
      let url9 =`http://localhost:8088/history/recordHistory`
      axios.get(url9,{
        params:{
          user_id:JSON.parse(sessionStorage.getItem('form')).id,
          manuscript_id:this.article.id
        }
      })
    })

  },
  methods:{
    thumbUping(){
      let tmp ={
        userId:JSON.parse(sessionStorage.getItem('form')).id,
        manuscriptId:this.article.id
      }
      if(this.loadInfo.thumbUp==true){
        let url = `http://localhost:8088/manuscript/cancelLove`
        axios.post(url,tmp)
      }
      else {
        let url = `http://localhost:8088/manuscript/loveAddOne`
        axios.post(url,tmp)
      }
      this.$router.go(0)
    },
    forwarding(){

    },
    collecting(){
      this.targetCollection.manuscript_id=this.article.id
      this.targetCollection.user_id=JSON.parse(sessionStorage.getItem('form')).id
      if(this.loadInfo.collect==false){
        let url = `http://localhost:8088/collect/getAllList`
        axios.get(url,{
          params:{
            user_id:this.targetCollection.user_id
          }
        }).then(res=>{
          this.collections=res.data
          console.log(this.collections)
          this.dialogVisible=true
        })
      }
      else{
        let tmp ={
          userId:this.targetCollection.user_id,
          manuscriptId:this.targetCollection.manuscript_id
        }
        let url1 = `http://localhost:8088/manuscript/cancelCollection`
        axios.post(url1,tmp)
        let url2 =`http://localhost:8088/collect/moveOutList`
        axios.get(url2,{
          params:{
            user_id:tmp.userId,
            manuscript_id:tmp.manuscriptId
          }
        }).then(res=>{
          if(res.data.success==true)
            alert("取消收藏")
          this.$router.go(0)
        })
      }

    },
    submitCollection(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let tmp ={
            userId:this.targetCollection.user_id,
            manuscriptId:this.targetCollection.manuscript_id
          }
          let url1 = `http://localhost:8088/manuscript/collectionAddOne`
          axios.post(url1,tmp)
          let url=`http://localhost:8088/collect/collect`
          axios.get(url,{
            params:{
              manuscript_id:this.targetCollection.manuscript_id,
              user_id:this.targetCollection.user_id,
              list_id:this.targetCollection.list_id,
            }
          }).then(res=>{
            if(res.data.success==true){
              console.log(res)
              alert('收藏成功')
              this.$router.go(0)
            }
            else
              alert('收藏失败')
          })
        }
        else {
          return false;
        }
      })
    },
    addFirstComment(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.comment.manuscriptId=this.article.id
          this.comment.userId=JSON.parse(sessionStorage.getItem('form')).id
          this.comment.username=JSON.parse(sessionStorage.getItem('form')).username
          this.comment.commentdate=makeDate(new Date())
          this.comment.love=0
          console.log(this.comment)
          let url=`http://localhost:8088/firstComment/add`
          axios.post(url,this.comment).then(res=>{
            if(res.data.success==true){
              alert("发表评论成功")
              this.$router.go(0)
            }
            else
              alert("发表失败")
          })
        }
        else {
          return false;
        }
      })
    },
    inform(index){

    },
    getFocus(){
      this.focus.userId=JSON.parse(sessionStorage.getItem('form')).id
      this.focus.friendId=this.article.userId
      this.focus.friendName=this.article.username
      this.groups=JSON.parse(sessionStorage.getItem('group'))
      this.dialogVisible1=true
    },
    cancelFocus(){
      let url=`http://localhost:8088/friend/deleteFriend`
      axios.get(url,{
        params:{
          user_id:JSON.parse(sessionStorage.getItem('form')).id,
          friend_id:this.article.userId,
        }
      }).then(res=>{
        console.log(res)
        if(res.data.success==true){
          alert("取消关注")
          this.$router.go(0)
        }
      })
    },
    submitGroup(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url=`http://localhost:8088/friend/addFriend`
          axios.post(url,this.focus).then(res=>{
            if(res.data.success==true){
              alert('关注成功')
              this.$router.go(0)
            }
            else
              alert('关注失败')
          })
        }
        else {
          return false;
        }
      })

    },
  }
}
</script>

<style scoped>
  .el-container{
    padding: 0;
    margin: 10px 5px 0 5px;
  }
  .el-aside{

  }
  .author-box{
    height: 100px;

  }
  .el-header{
    margin-top: 0;
    text-align: left;
    line-height: 1.4;
    max-width: 660px;
  }
  .el-main{
    height: auto;
    line-height: normal;
    margin: 50px 0 50px 0;
    text-align: left;
    padding: 0;
  }
   h1{
    font-size: 28px;
    color: #222;
    margin-bottom: 14px;
    font-weight: 700;
    margin-block-start: 0.67em;
    margin-block-end: 0.67em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
  }
  .info span{
    margin-right: 18px;
    color: #9b9b9b;
    transition: .3s;
    -webkit-transition: .3s;
  }
  div{
    margin-left: 5px;
  }

</style>
