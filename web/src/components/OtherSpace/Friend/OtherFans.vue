<template>
  <div>
    <div v-for="(item,i) in fans" :key="i" class="focus">
      <div class="content">
        <a class="title">{{item.username}}</a>
      </div>
      <div class="focus-action">
        <el-button @click="sendMessage(i)" style="margin-right: 100px">发消息</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'OtherFans',
    data() {
      return {
        fans: [],
      }
    },
    mounted() {
      let url = `http://localhost:8088/friend/getAllFansId`
      axios.get(url, {
        params: {
          user_id: JSON.parse(sessionStorage.getItem('other')).id
        }
      }).then(res => {
        console.log(res)
        let tmp = res.data
        for (let i in tmp) {
          let url = `http://localhost:8088/user/selById/${tmp[i]}`
          axios.get(url).then(res => {
            this.fans.push(res.data)
          })
        }
      })
    },
    methods: {
      sendMessage(index) {
        let id = this.fans[index].id
        this.$router.push({
            path: '/space/message/chat/' + id,
            query: {
              id: id
            }
          }
        )
      }
    }
  }
</script>

<style scoped>
  .focus{
    border-bottom: 1px solid #eee;
    display: block;
    padding: 30px 0;
    position: relative;
    line-height: 1;
  }
  .content{
    padding: 0 200px 0 0;
    position: relative;
    margin-left: 88px;
    margin-top: 10px;
    float: left;
  }
  .title{
    font-size: 16px;
    line-height: 1;
  }
  .description{
    line-height: 14px;
    font-size: 12px;
    color: #6d757a;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .focus-action{
    margin-right: 10px;
    margin-top: 10px;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
