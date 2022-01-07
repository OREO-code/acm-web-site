<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      @select="handleSelect">
    <router-link to="/index" style="text-decoration: none">
      <el-menu-item index="21">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </template>
      </el-menu-item>
    </router-link>
    <router-link to="/sys/notice" style="text-decoration: none">
      <el-menu-item index="2">
        <template slot="title">
          <i class="el-icon-message-solid"></i>
          <span slot="title">公告</span>
        </template>
      </el-menu-item>
    </router-link>
    <router-link  to="/sys/rotation" style="text-decoration: none">
      <el-menu-item index="3">
        <template slot="title">
          <i class="el-icon-picture"></i>
          <span slot="title">轮播图</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/intro" style="text-decoration: none">
      <el-menu-item  index="4" >
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span slot="title">资讯</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/time" style="text-decoration: none">
      <el-menu-item index="5" >
        <template slot="title">
          <i class="el-icon-time"></i>
          <span slot="title">时间线</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/member" style="text-decoration: none">
      <el-menu-item index="6">
        <template slot="title">
          <i class="el-icon-medal-1"></i>
          <span slot="title">成员风采</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/file/notice" style="text-decoration: none">
      <el-menu-item index="7">
        <template slot="title">
          <i class="el-icon-s-platform"></i>
          <span slot="title">公示文件</span>

        </template>
      </el-menu-item>
    </router-link>

    <el-submenu index="7">
      <template slot="title">
        <i class="el-icon-s-opportunity"></i>
        <span>图灵杯</span>
      </template>
      <router-link to="/sys/file/pdf" style="text-decoration: none">
        <el-menu-item index="7-1">
          <i class="el-icon-s-data"></i>
          <span>pdf题解</span>
        </el-menu-item>
      </router-link>
      <router-link to="/sys/file/mp4" style="text-decoration: none">
        <el-menu-item index="7-2">
          <i class="el-icon-s-unfold"></i>
          <span>mp4题解</span>
        </el-menu-item>
      </router-link>
<!--        <el-menu-item index="7-3">公示文件</el-menu-item>-->
    </el-submenu>



    <router-link to="/sys/manage" style="text-decoration: none">
      <el-menu-item index="8">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title">权限管理</span>
        </template>
      </el-menu-item>
    </router-link>

  </el-menu>
  <!-- <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      menu-trigger="click"
    @select="handleSelect"
      router>

    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </template>
    </el-submenu>

    <router-link to="/sys/notice" style="text-decoration: none">
      <el-menu-item index="2">
        <template slot="title">
          <i class="el-icon-message-solid"></i>
          <span slot="title">公告</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link  to="/sys/rotation" style="text-decoration: none">
      <el-menu-item index="3">
        <template slot="title">
          <i class="el-icon-picture"></i>
          <span slot="title">轮播图</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/intro" style="text-decoration: none">
      <el-menu-item  index="4" >
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span slot="title">介绍文档</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/time" style="text-decoration: none">
      <el-menu-item index="5" >
        <template slot="title">
          <i class="el-icon-time"></i>
          <span slot="title">时间线</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/member" style="text-decoration: none">
      <el-menu-item index="6">
        <template slot="title">
          <i class="el-icon-medal-1"></i>
          <span slot="title">成员风采</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/tulin" style="text-decoration: none">
      <el-menu-item index="7">
        <template slot="title">
          <i class="el-icon-medal-1"></i>
          <span slot="title">文件管理</span>
        </template>
      </el-menu-item>
    </router-link>

    <router-link to="/sys/manage" style="text-decoration: none">
      <el-menu-item index="8">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title">权限管理</span>
        </template>
      </el-menu-item>
    </router-link>


  </el-menu> -->
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {
      activeIndex:1
    }
  },
  computed:  {

  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex=keyPath

      console.log('路径'+keyPath+this.activeIndex)
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo {
  height: 100%;
}

</style>