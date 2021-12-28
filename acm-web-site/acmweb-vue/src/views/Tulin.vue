<template>
	<div id="app">
		  <div class="submit_tuling">
		            <div>
		              <img class="acmlogo"
		                   src="data:image/png;base64,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"
		                   alt="">
		            </div>
		            <div class="tulinintro">
						<p class="text_tuling">
						  NEUQ-ACM第九届“图灵杯”程序设计竞赛（个人赛）如约而至！
						  什么？听说你拥有严谨缜密的逻辑思维但无处施展？
						  什么？听说你热爱程序设计想检验自己的代码功底？
						  快来报名参加本届“图灵杯”程序设计竞赛（个人赛）吧！
						  <br><br>
						  报名时间：2021.12.25—2022.01.14
						  <br>
						  比赛时间：2022.02.15 13:00—17:00
						</p>
					</div>
		            <el-button type="primary" class="btn_submit" @click="dialogVisible = true">立即报名</el-button>
		            <el-dialog
		                title="提示"
		                :visible.sync="dialogVisible"
		                width="30%"
		                :before-close="handleClose">
		              <span>微信扫描二维码立即报名</span>
		              <img
		                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAATkAAAD/CAMAAAC9+VHeAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAD/UExURUxpcVia/Pz8+UiS//r8/7gAALQCAubl8Pv49fz89vLx2/f6/vvfWP/cG/7eKcI0NNFrNf3jTP7fM/7dKv/cINBlZoWx9v34+MtSU2Ce+vz9//7gO3Gm+bodHbkSE+Gdnv79/bYICNyOjr4lJe7Gxv///////9V4eP3mX+anhf///0eR/7MAAP/ZDrIAAP/YCEuU/kGO//Xg4LwdHUSQ///8+qTF+uDr/Yu2+v/fD+eur//cHfvz8/jp6b4oKMdERP/97P/fMv7rfWyk+//nZP7zsf72x//iSe/0/bXP+tTj+//62//vmMba+0iR/bgAAHyu+rYODu7Gx9V0ddiAgIJCwokAAAAqdFJOUwD9F8T+84sDCA8jLj7uxv4iWXOs3f5b4/3MRZWVSrf9ktj7e+S+cOl98HKalBUAABDNSURBVHja7J0NU9tIEoYRqzs+kkBIILDZuyOVTbIpyV8ykmz8ASQOwTLYZUj+/2+50cg2mpmWNCONPAKpq3ar1rXrdR66p9+3p2U2NqqooooqqqiiiiqqqKKKKqqooooqqqiiiiqqqKKKKqqooooqqniWsbX58mC3wiAcr9+8PeyfH7492KlYCMT2yzeH9fPzOvqrf/jX7lZFhC92Dt5ibEGcn7+qEo8r3Q7eHD5iW8CrH/71crtiE3+67dVpbovE23tTtYvo0+3tKwjbEh5KvKpqAQ2ybAqhaDabJLv+3puXVbugsO2x2Po/vn+rN+mqfXvwugK24sZUKUq2m7tbw7i6/tFvNtmqrRJvmW7kkdasf/v+0zGCuL27qdNVixJvs+y99IBNt/q3H9dXRiicn0DVllqnoCpF0u2cOtxu7ghsQbBVi9vFblmr9BWNrX7z/dYx4EBVy8AroU6Bq/TxcAMDqtpy6ZStl38dAhLk+spIDL9q66xOKUfivQaqtI8lCF/c3vUhd/Hc28UOU6X4cIuvUirpvpXP1voGi+6l/uF2JYKN0sUlSDywl/IdbgtsuD8069GBdcpzaxfbQJWKHG5OIrZn2S4Ag5UsQSgpx4FtmXiHX5+JTtlhq5TxVwnY+rzYQrb2qcPbxgaLx19FYfshhu15uAu/SvuAvxJopWmwhWztk0y8LdZgCR1u2C2kxPaYeE+vXYBVyn+4XV0zDjUdvP7e190nJPK2dtkqFZAgnArk+bWLbFUqFdtKpzwBd7HNjEGwBHHyUiDcibd3UOR2EVGlV2qxhRKvoO1i54BSvEKHWxYFItIuCpd4/rCynvpwy65Anmq72KUNVrPOL0FkKRD+xCtKu2Ckm0iV5tBKqclCn73pLkS7CLZByM/K7a+cnwIzkBTUcFu/BX426tvFJtMU0GflPdxybaULbFePE3ga3it17YKRbiL+Kn9s34mPEsArwjyFkW4ih1u+CsT/JNDdBrTcs3Zby9xgCUgQ4KcvHZvImsAadQpbpfz3V7lj+3F36wiOStdka6Eq5T7cnOtvzdyqlF9CAmtlubeLTXYbROD+KvVsXKiRcqrImzqTePmt5G1+Zdbt632BG6ycjFZT7Lb7EV6fcRcHOZF7D13eiV3/SYcX3xFiLd8NW+wfciL3QgdVf1MZPKyEUmC7pZXxotj1HMlFWCYV8AIBmQLbnf//bUK3v7mRe6/H2PQ0ZRPAa6ZspCKtafnBfUFH/LiI1pI/ObnwRGclCyMvjg01VLKz0x05P3JO4oAoTQkJwWMdKfciFIuNfqOcyP3nXTjnkuAZecBLpz+A/bEI/sdfciH3R0vrcG615QIvysgLv3FU2g56//s7J3KtDv9KYDp4kfPhtPoDxgbzb+//699rJheRMdh/X0mAFxh5Q4L+iK32oRJyEX9okYuciPdJ2UgX2EQOyfaFInJRmZcG3qL8RY08IduaTUGp3t5XRy5iAJfmz+9nXj+V/gBkG35ugH0nXT9WVa26641zhCdDf0RjG3z6eNRWds7NLa07HfN50pQSNiM22NXo+qePJ2e1i6Gyc24ysy07Gh44F8gJHnvGRmIbfDpF2GqN+9/qOoQ+skzTtmaq4d2C2CABqB/jbGs0arXaWZskd7bODuEhcmYaeI5EbLT+iMP2z+UCW612f+QoJDfWbNNcwhuB8G5BeDdy4LH6gxObT65nKCRnuJa5ikh4V/nAY/VHpOnD2O5D2NAxdzlQSq7Tsk2TgjcZc21DpL1CiBobxWLbJ7H5KffRUErO6FomGT48d6LnCg9wenFFymJDcdFWTG46M5mwrZYQPLFZKODxxLItSLlfhmJyWJisD96tOLYaGGcPqskthcka4DnM2CgtNhQnjnJyj8JEAN7PKHgi+iMGW+8oFhsq1lNDOTm2R7DwOtzw4BEmoD9iSDsP+7VYbCj2hwUgN2kBSWfbjy9aVmvudqL2YhLhXQlh89+3fZHArUZZVkXk9DmQdNooXMS2Zbfm0EQKTKbwLDTijiN+uu58TCLH9gcV5ABhYmsTOhX9oUoUPGCQjMd54IDe39dLmq4P9+PR3Z84hSA3HjHlqnUM3V1OA+y08Fj9EXUpQZ2jTu8+Fh1tWVWRA4SJfzU7DpSeNtfSZl6Ta7vQeegNmLvTo1hyl4OCkGOFidbRJ/p0hl62u87Ur1s7nHngXCDucZxIbIOHX/v0oC3AEIOOsazKyLHCBJEbubr/sj03OgjsbNSykuHBX4MTj63RaADk4ppE46JdGHKMMPHJaR0fWUDOno87rg/PFIUXi62BHSlEzhheRqK7PzIKQ44RJj45q6t7S3Ktqf9vTSh4ZtQgeQkvBtvR/srIg+RimsRZTyk5spt5M4Bca4qIBuS6utHxIWF4XLNQ/OQmfFs27B1dNELzD5BcTJM4Gagk50w7ccIEk7NH40nL8smhlBvPrQCSjijb4cyLmIVeXd+B2B5IbJHkjCgnAVjWtebc3CXvrC2WnGm66HVMbqJ7qEEEkLoIqTtiZqFjjhkTnW2x5KKaxH5bMbkRUa8dUpgsyGmT8WikY3JTdMRheFoLKRXWYSTDa5+enDWgIWUEuYgmwY4010yuO5sQSUcKE20cXMXOx9M5JufD9eYYnkmQs/ngtXsR2GLIwU0CsqzrJWd1Y4RJy51gBzFzjbEx8cmNfSadoD+EybVmVuI4LzLb4skZg18NPsu6ZnLaOEaY2FprZcN8cno3UCC4qsPk3Oncjp2Ftk8v72OwxZGDmsR9z1BOzvYIcpQwWRajr0cwOQvvAXRtKudc3Xe9NtFtQ/DaJwnYYskNTuMt6zDseNdHDnXNhIkJrkZv4rYCcn5/mJkgOYRUI+F5i6MqCVssOWN40oixrE7vcqgk50hPQQuTZcyQ7/fPORf3BxMkh0cr3jwEz3aX5GpZyDkPZJMgLevwaF9JzpmWa8QIEyLx3GV/wPBocuif9U7QfbUlXTnkjMHvRqRldR7OLhWRIyWdHneVo3W9FTyI3FSbewt4LZnkjDYxHiYsK6KqiJxJSjr4KoeZjUzcGURuhmeenVXVyyI36EX2B9R5VZGjJN14nnB/iJfsdNf2yS1l3IrcYmA80T2p5IgmQVjWwWlDGTmbknSebcYHzjzNJzd2RzN8ooXI4ctGTzY5dJo1QMvqmzNV5EzL4xEmFDz0t/li4ITgkeRMUzq5UJMgLKvTo6p3veTmBo8wYZVKMFjSp92RF6rWfMg9NgnCsuIqVkbOpMbEMcKEXbLr+j5hjM69BbkZnhfnQG7l/MOWNVB66shRkk7vciZdaEMRlTgmZ2tTF4HPgRxSvA3GsgbTAHXkbFLSIbFhCgR2qK7t67vOyA4mUxLIDdpwkwhbLWPxkjJypj1N3jFJ6LbYUyDz5coiN7ykR3ADfzxMWNZBMDBWSI6SdMnCBHYYwWxEd7WZDHJnl/QMro0UCGFZF11DZc5Rj6jzCJOoJTv/P/ckKOHhReOUbRKEZV2OnxSSoyUdtzBh2FmjsTHWVwWfjRxziz84IvrD8opCKTmqR3ALE7ZkJ/p85HUWmjgbuQb1ZJLhtH8PKBWsmhxt+0WECb10Nx1ZlqbZEsjFDM0JL6uUHNUjBIUJiU573HnKSC5i64aedyolR9l+cWHC3F3IIFdrHMU42dWdmFJyjKTz0h50RGQlF1evj1diasgtAVG2P6UwkU6uASzuEypYFbmOq1kLSUfZ/rTCRDK5WuN38oBdBTlftS6Gk5TtzyBM5JKL2IMIX8KqIYfnaxpiR9v+9MJEKrmoRbnweF0VueAyy7YoSQc+uamCHLgpR+zpKCSHb5jtLt0jrEKQA0UdscuplJxftJ4hXZjIIMd8PQR1oaOcHFDD2XuEFHJAvZKX/oUjJ0GYyCHHrraSi2GFI2dMMiedJHJ0vQ7I5eHikcsuTCSRo00YuWZSRHKZhYkscuTT004veg+xGOSym1dp5IjHp+lNxAKSyyxMpJEL3+zTi4hFJJdZmMgjF8LDPNFURHJZhYlEcquLVlIFF5VcVmEikVzt4gFcfVVEbjLJV5jIJLccmrCPRyiZCc/A5/GlCROp5AITNjht1IpAzvL3Lye5CROp5IKhCfAEnbJtV6sVk3jZhIlUctiEIRVcGHL4m4O0LvSVVZmFiVxyvgljnsdRft/qbxB2pAsTyeRq++2H+4KRw/DgrzKIe0Bi3eTuj4BHNpWTCxJvmvyVEgrJIXa1IpLzTzz2l71kESbyydWKSs6ceTKFSZnI2aMxK0ysilwyObM1lShMSkXOnuvyhEmpyJnaRJ4wKRc5+q4/izApFzlTojApGTnTlSZMykZOnjApG7mWJ0uYlI0cJEzSTdXLRg5Sw+mESenISRMmpSMnTZiUjxwoTKyKXDI5aGLiVuR4ck6SMCkhOVOOMCkjuZaUiUkZyckRJmUkJ0eYlJKclIlJKclJESblzLmZBGFSTnIyrnLKSU7GxKSk5CQIk5KSg4SJN6vIcZCzMwuTspIztazCpLTkMguT0pKD1LDQxKS05DILk/KSA9TwWESYlJccsNgkJExKTA5QwyLCpMTkIDUsIEzKTA4Y0wkIkzKTyyZMSp1zgDCZcguTUpPLJExKTQ4UJlXO8ZDLIkzKTS6LMCk5uQzC5BmRe/fHn8LkMgiT50Nua3dj+8WXz7pYzrGLTTrnxGT95IZ//3crl5zD7/oIj49cemGybnLHH97vbOQbO+8/HHOTA9QwpzBZK7njz182N9YRmwhel++g93/zLZ10XD1ifeT0z19ebKwvdt/92eL5dQY2oIa5hMmayA0Rtq2N9cb2/9s7l52GYSCKuhGQR7vIqpWqtrSKygKxiAQLNnxCa/X/f4bEednJxB7nncaXXUSZyYl9Z+IBsT2g4DVsTAYgR+njtHHIGHK3h5UKXtPGpG9ylNLL29EmoynatatQd354C0cml2Dzycjy5LsWWHSYxqRHcpS+nsZcbWjLa9aY9EUuxhb4ZDqSWB7UmKhPTHohR+nX5c23ycRkxZYXdtWYdE+OYTv6ZIqy4Xrx06Qx6ZocM7dpYkstb121vEaNSafkJmdutZZXggc1JvfByEW7dA7YMssT6kX5H+Vg/nKzI3KxuQVHm8xHouU1aEy6IBc3vNM2NyU86JhOUSPak5sptlK90G9MWpKbSU1A1Itf3cakFbnY3OaNrXi/0G5MWpDbBfOqCYpiy47gxV9+vfdBbnfeW0+DLakX/OAMcWLSiNzAB+MD1gsBnrwx0ScXY3PIsyodnCEaEy1yf58vA0z9Rre8HJ60MdEgR7/fI3MjS5D3cd2pGhMsueTIjSxGSb2QNSY4cmye4JBlydmfr5JRDoJc9J6wPGyZ5dVPHVXkktcrmyxVdu3UUUqOTf0WjK04FQDgScgt0txqujwAXh05g011BA+SS8YwtsElWp54BF8lt/SagK0XJXKUPi4GG6peCOQicwuMuaEsbxUW5Ji5GWzIFnl7uKXkTE3QfrHdM20MNiMjozl5F6wx4jvDRQfvVYdAmnFZiI/aSqFvoEn4TrDloQRxVxF5u7yK7JXYgNB8OlhsbiV+j+yAp8Xljnp86ffGyVq8uOR1Vgq0cBR34NSEV8RvDU54XGLa/LW6BHJuLF8vk8hOGtupBuavKdd7Ed4TwivityaXA7JcS4AHrHzJT0gTX2dKsmeflH2wFCb9qm55Vf6l6EV8tx904nrJF3ku7lJdAgU4IXE1uvImAwJzO06SvwtFxzw7WP+JVdIVtKKQxAAAAABJRU5ErkJggg=="
		                  alt="">
		              <span slot="footer" class="dialog-footer">
		              </span>
		            </el-dialog>
		          </div>
		          <el-card class="box-card">
		            <div slot="header" class="clearfix">
		              <span>历年真题</span>
		            </div>
		            <div class="text item">
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1790" target="_blank">[NEUQ]第八届“图灵杯”个人赛真题重现
		              </el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1789" target="_blank">[NEUQ]第七届“图灵杯”个人赛真题重现
		              </el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1788" target="_blank">[NEUQ]第六届“图灵杯”个人赛真题重现
		              </el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1787" target="_blank">[NEUQ]第五届“图灵杯”个人赛真题重现
		              </el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1786" target="_blank">[NEUQ]第四届“图灵杯”个人赛真题重现
		              </el-link>
		            </div>
		          </el-card>
		          <el-card class="box-card_2">
		            <div slot="header" class="clearfix">
		              <span>历年题解文档</span>
		            </div>
		            <div class="text item">
		              <el-link type="primary" href="https://maiimg.com/doc/?e=dnspIdyMb4LuU6" target="_blank">
		                [NEUQ]第八届“图灵杯”个人赛题解
		              </el-link>
		              <br>
		              <el-link type="primary" href="" download>[NEUQ]第七届“图灵杯”个人赛题解</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1788">[NEUQ]第六届“图灵杯”个人赛题解</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1787">[NEUQ]第五届“图灵杯”个人赛题解</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1786">[NEUQ]第四届“图灵杯”个人赛题解</el-link>
		            </div>
		          </el-card>
		          <el-card class="box-card_3">
		            <div slot="header" class="clearfix">
		              <span>历年真题讲解视频</span>
		            </div>
		            <div class="text item">
		  <!--            <a href="http://10.53.184.164:8082/download/孙晶玉.jpg">-->
		  <!--              <el-button type="primary" href="">下载</el-button>-->
		  <!--            </a>-->
		  <!--            <el-button class="downloadFile" type="text" @click="setup">下载</el-button>-->
		  <!--            <br>-->
		              <el-link type="primary" href="http://10.53.184.164:8082/download/henrik.ico">[NEUQ]第八届“图灵杯”个人赛真题重现</el-link>
		              <br>
		              <el-link type="primary" href="http://10.53.184.164:8082/download/1、知识点汇总.docx">[NEUQ]第七届“图灵杯”个人赛真题重现</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1788">[NEUQ]第六届“图灵杯”个人赛真题重现</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1787">[NEUQ]第五届“图灵杯”个人赛真题重现</el-link>
		              <br>
		              <el-link type="primary" href="http://newoj.acmclub.cn/contests/1786">[NEUQ]第四届“图灵杯”个人赛真题重现</el-link>
		            </div>
		          </el-card>
	</div>
</template>

<script>

export default {
  name: "demo",
  data() {
    return {
      activeIndex: '3',
      activeIndex2: '3',
      publicPdf,//文件静态地址
      dialogVisible: false,
      file: "孙晶玉.jpg"
    };
  },
  methods: {
    test() {
      this.$axios.get("http://10.53.184.164:8082/download/孙晶玉.jpg").then(res => {
        console.log(res)
      })
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    setup() {

      axios
          .get('http://10.53.184.164:8082/download/henrik.ico')
          .then((response) => {
            console.log(response)
            // const blob = new Blob([response.data])
            // const blobUrl = window.URL.createObjectURL(blob)
            // const tmpLink = document.createElement("a")
            // tmpLink.style.display = 'none'
            // tmpLink.href = blobUrl
            // tmpLink.setAttribute('download', 'abc.pdf')
            // document.body.appendChild(tmpLink)
            // tmpLink.click()
            // document.body.removeChild(tmpLink)
            // window.URL.revokeObjectURL(blobUrl)
          })

    },
    // downloadFile() {
    //   // location.href = http + downloadfile + '?idFile=' + row.id + '&fileName=' + row.originalFileName
    //   axios
    //       .get('/component/数据结构 习题答案.pdf', {
    //         responseType: "arraybuffer",
    //       })
    //       .then((response) => {
    //         const blob = new Blob([response.data])
    //         const blobUrl = window.URL.createObjectURL(blob)
    //         const tmpLink = document.createElement("a")
    //         tmpLink.style.display = 'none'
    //         tmpLink.href = blobUrl
    //         tmpLink.setAttribute('download', 'abc.pdf')
    //         document.body.appendChild(tmpLink)
    //         tmpLink.click()
    //         document.body.removeChild(tmpLink)
    //         window.URL.revokeObjectURL(blobUrl)
    //       })
    // },
  }
}
</script>

<style scoped>

.submit_tuling {
  /*position: absolute;*/
  margin: 0 auto;
  width: 800px;
}

.acmlogo {
  /*position: relative;*/
  /*left: 30px;*/
  /*margin: 10px;*/
  height: 250px;
  float: left;
}
.tulinintro{
	width: auto;
	height: auto;
	background-color: rgba(255, 255, 255, 0.5);
	border-bottom-left-radius: 3.125rem;
	border-bottom-right-radius: 3.125rem;
}
.text_tuling {
  padding-top: 40px;
  text-align: left;
  color: black;
  font-size: 18px;
  padding-bottom: 3.75rem;
  
  /*float: left;*/
}

.btn_submit {
  position: relative;
  right: 150px;
  margin-top: 10px;
  width: 160px;
}

.el-header {
  display: none;
}

.el-main {
  /*background: #4b6cb7; !* fallback for old browsers *!*/
  /*background: -webkit-linear-gradient(to right, #182848, #4b6cb7); !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to right, #182848, #4b6cb7); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/

  /*background: #2193b0;  !* fallback for old browsers *!*/
  /*background: -webkit-linear-gradient(to left, #6dd5ed, #2193b0);  !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to left, #6dd5ed, #2193b0); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/

  /*background: #076585;  !* fallback for old browsers *!*/
  /*background: -webkit-linear-gradient(to left, #fff, #076585);  !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to left, #fff, #076585); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/

  /*background: #536976;  !* fallback for old browsers *!*/
  /*background: -webkit-linear-gradient(to left, #292E49, #536976);  !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to left, #292E49, #536976); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/

  /*background: #C9D6FF; !* fallback for old browsers *!*/
  /*background: -webkit-linear-gradient(to right, #E2E2E2, #C9D6FF); !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to right, #E2E2E2, #C9D6FF); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/
  background-color: aliceblue;
  color: #333;
  /*text-align: center;*/
  line-height: 20px;
  margin: 0;
  height: 660px;
  /*2000*/
}

.el-header, .el-footer {
  background-color: #545C64;
  color: #333;
  text-align: center;
  line-height: 60px;
}

body > .el-container {
  margin-bottom: 40px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {

  position: absolute;
  top: 450px;
  left: 20px;
  width: 480px;
}

.box-card_2 {
  position: absolute;
  top: 450px;
  left: 525px;
  width: 480px;
}

.box-card_3 {
  position: absolute;
  top: 450px;
  left: 1030px;
  width: 480px;
}

.downloadFile {
  margin: 0;
  padding: 0;
}
</style>
