"use strict";(self["webpackChunktp_s09_s09_vue_enjoytrip_front"]=self["webpackChunktp_s09_s09_vue_enjoytrip_front"]||[]).push([[198],{1038:function(t,e,r){r.r(e),r.d(e,{default:function(){return u}});var s=function(){var t=this,e=t._self._c;return e("b-container",{staticClass:"bv-example-row mt-3"},[e("b-row",[e("b-col",[e("b-alert",{attrs:{show:""}},[e("h3",[t._v("글목록")])])],1)],1),e("b-row",[e("b-col",[e("b-alert",{attrs:{show:"",variant:"danger"}},[t._v("삭제처리중...")])],1)],1)],1)},a=[],i=(r(7658),r(9477)),l={name:"BoardDelete",created(){let t=this.$route.params.postId;(0,i.jX)(t,(({data:t})=>{console.log(t);let e="삭제 처리시 문제가 발생했습니다.";"success"===t.msg&&(e="삭제가 완료되었습니다."),alert(e),this.$router.push({name:"boardlist"})}),(t=>{console.log(t)}))}},o=l,n=r(1001),c=(0,n.Z)(o,s,a,!1,null,null,null),u=c.exports},3900:function(t,e,r){r.r(e),r.d(e,{default:function(){return v}});var s=function(){var t=this,e=t._self._c;return e("b-container",{staticClass:"bv-example-row mt-3"},[e("b-row",[e("b-col",[e("b-alert",{attrs:{show:""}},[e("h3",[t._v(t._s(t.boardType)+"글목록")])])],1)],1),e("b-row",{staticClass:"mb-1"},[e("b-col",{staticClass:"text-right"},[e("b-button",{attrs:{variant:"outline-primary"},on:{click:function(e){return t.moveWrite()}}},[t._v("글쓰기")])],1)],1),e("b-row",[e("b-col",[e("b-table",{attrs:{hover:"",items:t.articles,fields:t.fields},on:{"row-clicked":t.viewArticle},scopedSlots:t._u([{key:"cell(createTime)",fn:function(e){return[t._v(" "+t._s(e.item.createTime.split("-")[0]+" / "+e.item.createTime.split("-")[1])+" ")]}},{key:"cell(title)",fn:function(r){return[e("router-link",{attrs:{to:{name:"boardview",params:{postId:r.item.postId}}}},[t._v(" "+t._s(r.item.title)+" ")])]}}])})],1)],1),e("BoardPagination")],1)},a=[],i=(r(7658),r(408)),l=function(){var t=this,e=t._self._c;return e("div",{staticClass:"overflow-auto"},[e("b-pagination",{attrs:{"total-rows":t.rows,"per-page":t.perPage,align:"center",limit:"9"},model:{value:t.curPage,callback:function(e){t.curPage=e},expression:"curPage"}})],1)},o=[];const n="boardStore";var c={data(){return{perPage:10,curPage:1}},computed:{...(0,i.rn)(n,["articles","totalPageNum"]),rows(){return this.totalPageNum}},watch:{curPage(){this.setPage()}},methods:{...(0,i.OI)("boardStore",["SET_CURRENT_PAGE"]),...(0,i.nv)("boardStore",["searchArticle"]),setPage(){this.SET_CURRENT_PAGE(this.curPage),this.searchArticle()}}},u=c,d=r(1001),m=(0,d.Z)(u,l,o,!1,null,null,null),b=m.exports,p={components:{BoardPagination:b},name:"BoardList",data(){return{fields:[{key:"postId",label:"글번호",tdClass:"tdClass"},{key:"title",label:"제목",tdClass:"tdSubject"},{key:"userName",label:"작성자",tdClass:"tdClass"},{key:"createTime",label:"작성일",tdClass:"tdClass"},{key:"hits",label:"조회수",tdClass:"tdClass"}]}},created(){this.searchArticle()},computed:{...(0,i.rn)("boardStore",["boardType","articles","currentPage"]),...(0,i.nv)("boardStore",["searchArticle"])},methods:{moveWrite(){this.$router.push({name:"boardwrite"})},viewArticle(t){this.$router.push({name:"boardview",params:{postId:t.postId}})}}},h=p,f=(0,d.Z)(h,s,a,!1,null,null,null),v=f.exports},2841:function(t,e,r){r.r(e),r.d(e,{default:function(){return u}});var s=function(){var t=this,e=t._self._c;return e("b-container",{staticClass:"bv-example-row mt-3"},[e("b-row",[e("b-col",[e("b-alert",{attrs:{show:""}},[e("h3",[t._v("글수정")])])],1)],1),e("board-input-item",{attrs:{type:"modify"}})],1)},a=[],i=r(4681),l={name:"BoardModify",components:{BoardInputItem:i.Z}},o=l,n=r(1001),c=(0,n.Z)(o,s,a,!1,null,null,null),u=c.exports},9896:function(t,e,r){r.r(e),r.d(e,{default:function(){return u}});var s=function(){var t=this,e=t._self._c;return e("b-container",{staticClass:"bv-example-row mt-3"},[e("b-row",[e("b-col",[e("b-alert",{attrs:{show:""}},[e("h3",[t._v("글보기")])])],1)],1),e("b-row",{staticClass:"mb-1"},[e("b-col",{staticClass:"text-left"},[e("b-button",{attrs:{variant:"outline-primary"},on:{click:t.moveList}},[t._v("목록")])],1),e("b-col",{staticClass:"text-right"},[e("b-button",{staticClass:"mr-2",attrs:{variant:"outline-info",size:"sm"},on:{click:t.moveModifyArticle}},[t._v("글수정")]),e("b-button",{attrs:{variant:"outline-danger",size:"sm"},on:{click:t.deleteArticle}},[t._v("글삭제")])],1)],1),e("b-row",{staticClass:"mb-1"},[e("b-col",[e("b-card",{staticClass:"mb-2",attrs:{"header-html":`<h3>${t.article.postId}.\n        ${t.article.title} [조회수: ${t.article.hits}]</h3><div><h6>${t.article.userName}</div><div>${t.article.createTime.split("-")[0]}년 ${t.article.createTime.split("-")[1]}월 \n        ${t.article.createTime.split("-")[2].split("T")[0]}일 시간 : ${t.article.createTime.split("-")[2].split("T")[1].split(".0")[0]}</h6></div>`,"border-variant":"dark","no-body":""}},[e("b-card-body",{staticClass:"text-left"},[e("div",{domProps:{innerHTML:t._s(t.message)}})])],1)],1)],1)],1)},a=[],i=(r(7658),r(9477)),l={name:"BoardDetail",data(){return{article:{}}},computed:{message(){return this.article.content?this.article.content.split("\n").join("<br>"):""}},created(){let t=this.$route.params.postId;(0,i.fq)(t,(({data:t})=>{this.article=t.result}),(t=>{console.log(t)}))},methods:{moveModifyArticle(){this.$router.replace({name:"boardmodify",params:{postId:this.article.postId}})},deleteArticle(){confirm("정말로 삭제?")&&this.$router.replace({name:"boarddelete",params:{postId:this.article.postId}})},moveList(){this.$router.push({name:"boardlist"})}}},o=l,n=r(1001),c=(0,n.Z)(o,s,a,!1,null,null,null),u=c.exports},4293:function(t,e,r){r.r(e),r.d(e,{default:function(){return u}});var s=function(){var t=this,e=t._self._c;return e("b-container",{staticClass:"bv-example-row mt-3"},[e("b-row",[e("b-col",[e("b-alert",{attrs:{show:""}},[e("h3",[t._v("글작성")])])],1)],1),e("board-input-item",{attrs:{type:"register"}})],1)},a=[],i=r(4681),l={name:"BoardWrite",components:{BoardInputItem:i.Z}},o=l,n=r(1001),c=(0,n.Z)(o,s,a,!1,null,null,null),u=c.exports},4681:function(t,e,r){r.d(e,{Z:function(){return b}});r(7658);var s=function(){var t=this,e=t._self._c;return e("b-row",{staticClass:"mb-1"},[e("b-col",{staticStyle:{"text-align":"left"}},[e("b-form",{on:{submit:t.onSubmit,reset:t.onReset}},[e("b-form-group",{attrs:{id:"userid-group",label:"작성자:","label-for":"userName",description:"작성자를 입력하세요."}},[e("b-form-input",{attrs:{id:"userName",disabled:!0,type:"text",required:"",placeholder:"작성자 입력..."},model:{value:t.article.userName,callback:function(e){t.$set(t.article,"userName",e)},expression:"article.userName"}})],1),e("b-form-group",{attrs:{id:"subject-group",label:"제목:","label-for":"title",description:"제목을 입력하세요."}},[e("b-form-input",{attrs:{id:"title",type:"text",required:"",placeholder:"제목 입력..."},model:{value:t.article.title,callback:function(e){t.$set(t.article,"title",e)},expression:"article.title"}})],1),e("b-form-group",{attrs:{id:"content-group",label:"내용:","label-for":"content"}},[e("b-form-textarea",{attrs:{id:"content",placeholder:"내용 입력...",rows:"10","max-rows":"15"},model:{value:t.article.content,callback:function(e){t.$set(t.article,"content",e)},expression:"article.content"}})],1),e("b-button",{staticClass:"m-1",attrs:{variant:"success"},on:{click:function(e){return t.$router.push({name:"boardlist"})}}},[t._v("글목록")]),"register"===this.type?e("b-button",{staticClass:"m-1",attrs:{type:"submit",variant:"primary"}},[t._v("글작성")]):e("b-button",{staticClass:"m-1",attrs:{type:"submit",variant:"primary"}},[t._v("글수정")]),e("b-button",{staticClass:"m-1",attrs:{type:"reset",variant:"danger"}},[t._v("초기화")])],1)],1)],1)},a=[],i=r(9477),l=r(408);const o="boardStore",n="userStore";var c={name:"BoardInputItem",data(){return{article:{postId:0,userName:"",title:"",content:""},isUserid:!1}},props:{type:{type:String}},created(){if("register"===this.type&&(console.log(this.userName),this.article.userName=this.userName),"modify"===this.type){let t=this.$route.params.postId;(0,i.fq)(t,(({data:t})=>{this.article=t.result}),(t=>{console.log(t)})),this.isUserid=!0}},computed:{...(0,l.rn)(o,["boardType"]),...(0,l.rn)(n,["userNo","userName"])},methods:{...(0,l.nv)(o,["writeArticle","searchArticle"]),...(0,l.nv)(n,["getUserInfo"]),onSubmit(t){t.preventDefault();let e=!0,r="";!this.article.userName&&(r="작성자 입력해주세요",e=!1,this.$refs.userName.focus()),e&&!this.article.title&&(r="제목 입력해주세요",e=!1,this.$refs.title.focus()),e&&!this.article.content&&(r="내용 입력해주세요",e=!1,this.$refs.content.focus()),e?"register"===this.type?this.registArticle():this.modifyArticle():alert(r)},onReset(t){t.preventDefault(),this.article.postId=0,this.article.title="",this.article.content="",this.moveList()},registArticle(){let t={userNo:sessionStorage.getItem("userNo"),title:this.article.title,content:this.article.content,boardType:this.boardType};console.log(t),this.writeArticle(t),this.moveList()},modifyArticle(){let t={postId:this.article.postId,title:this.article.title,content:this.article.content};(0,i.cn)(t,(({data:t})=>{let e="수정 처리시 문제가 발생했습니다.";"success"===t.msg&&(e="수정이 완료되었습니다."),alert(e),this.moveList()}),(t=>{console.log(t)}))},moveList(){this.$router.push({name:"boardlist"})}}},u=c,d=r(1001),m=(0,d.Z)(u,s,a,!1,null,null,null),b=m.exports}}]);
//# sourceMappingURL=board.9ee11f94.js.map