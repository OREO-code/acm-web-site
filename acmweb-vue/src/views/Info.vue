<template>
  <el-container>
    <el-aside width="10%"></el-aside>
    <el-aside width="20%">
      <el-card class="box-card_2" shadow="hover" >
        <div slot="header" class="clearfix" style="text-align: left">
          <span>总览</span>
        </div>
        <el-card class="box-card_2" shadow="hover" >
          <el-table
              max-height="400px"
              :data="Introduces"
              style="width: 100%;height: 50%">
            <el-table-column
                align="center"
                label="标题"
                width="150%">
              <template slot-scope="scope">
                <span style="text-align: center">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
                align="center"
                label="日期"
                column-key="date">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span >{{ scope.row.created }}</span>
              </template>
            </el-table-column>

          </el-table>

        </el-card>
      </el-card>

    </el-aside>
    <el-main><router-view/></el-main>
    <el-aside width="10%"></el-aside>
  </el-container>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {

      Introduces: [],

    };
  },
  methods: {
    getFileList() {
      const _this = this
      this.$axios
          .get('http://101.43.16.42:8082/getAllIntroduce')
          .then((data) => {
            _this.Introduces = data.data.data;
            console.log(data)
          })
          .catch((error) => {
            console.log(error)
          });
    }
  },
  created() {
    this.getFileList();
  }
}
</script>

<style scoped>

</style>