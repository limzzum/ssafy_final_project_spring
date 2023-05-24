<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <div>
  <b-tabs content-class="mt-3">
    <b-tab title="내 프로필" active>
      <table style="width: 300%; text-align: start; ">
        <thead>
          <th>아이디</th>
        <th>이름</th>
        <th>이메일</th>
        </thead>
        <tbody >
          <tr style="border-bottom: 1px solid rgb(228, 223, 223); border-top: 1px solid rgb(228, 223, 223);">
          <td >{{ userInfo.userId }}</td>
          <td>{{ userInfo.userName }}</td>
          <td>{{ userInfo.email }}</td>
          </tr>
          
        </tbody>
       
      </table>

    </b-tab>
    <b-tab @click="routeMyBoard()" title="작성한 글 보기">
  
    <div style="display: flex;width: 170%;">
      <router-view/>
    </div>
    </b-tab>
 
    <b-tab title="프로필 정보 수정" >
   



                <div class="col-12">
                 
                        <table class="table table-borderless display-flex p-3"
                               style="text-align: center; vertical-align: middle; width:170%">
                            <thead>
                            </thead>
                            <tbody>
                            <tr>
                                <td colspan="2">
                                    <label for="originalpw">기존 비밀번호</label>
                                    <input v-model="user.curPwd" type="password" class="form-control" id="originalpw" name="userPwd"
                                           placeholder="" required/>
                                </td>
                                <td rowspan="2">
                                    <button type="button" class="btn btn-primary btn-lg btn-block m-1"
                                            id="changePW" v-on:click="updateUser()">비밀번호 변경
                                    </button>
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <label for="newPw">새 비밀번호</label>
                                    <input v-model="user.newPwd" type="password" class="form-control" id="newPw" name="newPwd" placeholder=""
                                           required/>
                                </td>
                                <td>
                                    <label for="newPwCheck">새 비밀번호 확인</label>
                                    <input v-model="user.checkPwd" type="password" class="form-control" id="newPwCheck" placeholder=""
                                           required/>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    <div class="col-md">
                       
                            <div class="custom-control custom-checkbox">
                                <input v-model="isConfirmCheck" type="checkbox" class="custom-control-input"
                                       id="deleteConfirm" required/>
                                <label class="custom-control-label" for="deleteConfirm">회원 탈퇴시 저장된 회원 정보가 사라지는 것을
                                    이해했으며, 회원 탈퇴에 동의합니다.</label>
                            </div>
                            <button class="btn btn-primary btn-lg btn-danger btn-block m-1"
                                    id="deleteUser" @click="deleteUser()">회원 탈퇴
                            </button>
                    
                    </div>
                </div>

    </b-tab>
  </b-tabs>
</div>
    </b-row>
    <b-row>
      <b-col></b-col>
      
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import BoardList from "@/components/board/BoardList.vue";
// import UserBoardList from "@/components/user/UserBoardList.vue";
const userStore = "userStore";

export default {
  name: "UserMyPage",
  components: { },
  data() {
    return {
      user: {
        curPwd: null,
        newPwd: null,
        checkPwd: null,
      },
      isConfirmCheck: false,
      
    }
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    
 
  },
  methods:{
    ...mapActions(userStore, ["userUpdate","userDelete"]),
    updateUser() {
      console.log(this.user.curPwd , this.user.newPwd , this.user.checkPwd)
      if (this.user.curPwd & this.user.newPwd & this.user.checkPwd) {
        if (this.user.newPwd === this.user.checkPwd) {
          let newUser = {
            "userNo": sessionStorage.getItem("userNo"),
            "curPwd": this.user.curPwd,
            "newPwd": this.user.newPwd
          };
          this.userUpdate(newUser);
          this.user.curPwd = null;
          this.user.newPwd = null;
          this.user.checkPwd = null;
        }
     
      }
    },
    deleteUser() {
      if (this.isConfirmCheck) {
        if (confirm('Are you ready?')) {
          this.userDelete();
        }
      
      }
      
    },

    routeMyBoard() {
      this.$router.push({ name: "myboardlist" ,props:{isMy:true}});
    }
  }
};
</script>

<style></style>
