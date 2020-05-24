<template>
  <div>
    <HomeHead></HomeHead>
    <div class="content_box">
      <el-row :gutter="10">
        <!--分页-->
        <el-col :span="6">
          <h2 style="margin-top:4px">热门内容</h2>
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
          <PictureCard :manuscript="card" ></PictureCard>
        </el-col>
      </el-row>
      <el-col :span="4">&nbsp;</el-col>
    </div>
  </div>
</template>

<script>
import HomeHead from '../Head/HomeHead'
import PictureCard from './picture-card'
import axios from "axios";
export default {
  name: 'Homepage',
  components: {HomeHead,PictureCard},
  data() {
    return {
      card_list: [],
      currentPage: 1,
      perPage: 8
    }
  },
  computed: {
    showAppointList() {
      return this.card_list.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    }
  },
  methods: {
    enter() {

    },
    handleSizeChange(val) {
      this.perPage = val
    },
    handleCurrentChange(val) {
      this.currentPage = val
    },
    placeData() {
      axios.post('http://localhost:8088/recommend/getAllManuscript').then(response => {
        let array = response.data
        for (let i = 0;i < array.length; i++){
          this.card_list.push(array[i])
        }
        console.log(this.card_list)
        // console.log(response.data)
      }).catch()
    }
  },
  mounted() {
    this.placeData()
  }
}
</script>

<style scoped>

</style>
