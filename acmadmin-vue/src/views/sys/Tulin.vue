<template>
  <el-container>
    <el-container>
      <el-main style="padding: 0;height: 660px">
        <el-table
            height="660"
            max-height="100%"
            :data="tableData"
            style="width: 100%;line-height: normal;"
            highlight-current-row="true">
          <el-table-column
              label="ID"
              prop="id">
          </el-table-column>
          <el-table-column
              label="文件名"
              prop="fileName">
          </el-table-column>
          <el-table-column
              label="文件地址"
              prop="fileUrl">
          </el-table-column>
          <el-table-column
              align="right">
            <template slot="header" slot-scope="scope">
              <el-upload
                  class="upload-demo"
                  action="http://101.43.16.42:8082/upload"
                  :headers="headers"
                  :show-file-list="false"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :upclick="upclick"
                  multiple
                  :on-exceed="handleExceed"
                  :file-list="fileList">
                <el-button size="small" type="primary" style="line-height: normal">点击上传</el-button>
              </el-upload>
            </template>
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="danger"
                  @click="open(scope.row.id)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
const http = 'http://101.43.16.42:8082/'
const delFile = 'delFile/'
const downFile = 'download/'
export default {
  name: "manager",
  data() {
    return {
      headers: {
        "Authorization": localStorage.getItem("token")
      },
      fileList: [],
      info: null,
      isCollapse: false,

      tableData: [],
      search: ''
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item) {
      this.$router.push({name: item.name})
    },
    handleDelete(row) {
      this.$axios
          .get(http + delFile + (row))
          .then((data) => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log(data)
          })

      console.log(index, row);
      console.log(row.date);
      // location.reload();
    },
    getFileList() {
      this.$axios
          .get('/file')
          .then((data) => {
            this.tableData = data.data.data.fileList
            console.log(data)
          })
          .catch((error) => {
            console.log(error)
          })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    upclick() {
      this.$message({
        message: '上传成功',
        type: 'success'
      });
    },
    open(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(row);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  computed: {
    noChildren() {
      return this.menu.filter((item) => !item.children)
    },
    hasChildren() {
      return this.menu.filter((item) => item.children)
    }
  },
  mounted() {
    this.getFileList()
    // this.handleDelete()
  }
}

</script>

<style scoped>
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-thumb {
  background-color: #eaecf1;
  border-radius: 3px;
}

body .el-scrollbar__wrap {
  overflow-x: hidden;
}

.el-header, .el-footer {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: space-between;
  background-color: #333;
  color: #fff;
  text-align: left;
  line-height: 60px;
}

.l-content {
  display: flex;
  align-items: center;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: left;
  line-height: 200px;
  height: 100%;
}

.el-main {
  padding-top: 0;
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  /*width: 200px;*/
  min-height: 720px;
}

.el-menu {
  border-right: 0;
}
</style>