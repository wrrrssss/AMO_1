<template>
  <div>
    <HomeHead></HomeHead>
    <div class="content_box">
      <el-row :gutter="10">
        <!--分页-->
        <el-col :span="10">
          <h2 style="margin-top:4px">推荐内容</h2>
        </el-col>
        <el-col>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[8, 16, 24, 32]"
            :page-size="perPage"
            layout="total, sizes, prev, pager, next"
            :total="card_list.length">
          </el-pagination>
        </el-col>
        <!--循环显示-->
        <el-col :sm="12" :md="6" v-for="(card, index) in showAppointList" :key="card.id">
          <picture-card :manuscript="card" ></picture-card>
        </el-col>
      </el-row>
      <el-col :span="4">&nbsp;</el-col>
    </div>
  </div>
</template>

<script>
  import PictureCard from './picture-card.vue'
  import HomeHead from "../Head/HomeHead";
  import axios from 'axios'
  export default {
    name: '',
    data() {
      return {
        card_list: [],
        currentPage: 1,
        perPage: 8
      }
    },
    components: {
      'picture-card': PictureCard,
      HomeHead,
    },
    created() {
      this.search()
    },
    computed: {
      showAppointList() {
        return this.card_list.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
      }
    },
    methods: {
      search() {
        console.log('lalala')
          axios.get('http://localhost:8088/recommend/personalizedRecommend',{
            params: {
              user_id: 1
            }
          }).then(response => {
            let array = response.data
            for (let i = 0;i < array.length; i++){
              this.card_list.push(array[i])
            }
            console.log(this.card_list)
          })
      },
      handleSizeChange(val) {
        this.perPage = val
      },
      handleCurrentChange(val) {
        this.currentPage = val
      }
    }
  }
</script>

<style scoped>
  .content_box{
    margin-top: 20px;
  }
</style>
