(self["webpackChunktp_s09_s09_vue_enjoytrip_front"]=self["webpackChunktp_s09_s09_vue_enjoytrip_front"]||[]).push([[498],{29498:function(t,e,r){"use strict";r.r(e),r.d(e,{default:function(){return a}});var s=function(){var t=this,e=t._self._c;return e("b-container",[e("b-col",{staticClass:"justify-content-center text-center"},[e("h2",{staticClass:"align-middle col-12 border border-primary p-3",staticStyle:{"text-align":"center",background:"rgba(192, 218, 255, 0.31)"},domProps:{textContent:t._s(`🛫나의 마음 속 1등 관광지는 바로 ${t.win.title}입니다!!!🛫`)}})]),e("b-row",{staticClass:"justify-content-center"},[e("b-col",{staticClass:"p-3 border border-primary"},[e("h2",{staticStyle:{"text-align":"center"}},[t._v("다른 사람들의 선택은...")]),e("b-table",{staticClass:"text-center align-middle",attrs:{hover:"",items:t.items,fields:t.fields,busy:t.isBusy,"tbody-tr-class":t.rowClass},on:{"update:busy":function(e){t.isBusy=e}},scopedSlots:t._u([{key:"cell(img)",fn:function(t){return[e("b-img",{staticStyle:{"max-height":"100px"},attrs:{src:r(25459)(`./${t.value}`),fluid:"",thumbnail:""}})]}},{key:"cell(wins)",fn:function(r){return[e("b-progress",{attrs:{striped:"",animated:"","show-value":"",height:"30px"}},[e("b-progress-bar",{attrs:{value:""+(.8*t.percentage(r.value)+20),variant:""+(r.item.id==t.win.id?"success":"warning")}},[t._v(t._s(t.percentage(r.value).toFixed(1))+"%")])],1)]}}])})],1)],1)],1)},n=[],o=r(90408);const c="worldcupStore";var i={name:"WorldcupResult",components:{},data(){return{isBusy:!0,fields:[{key:"img",label:"이미지",tdClass:"col-1"},{key:"title",label:"관광지명",tdClass:"col-1"},{key:"content",label:"설명",tdClass:"col-3"},{key:"wins",label:"1등 비율",tdClass:"col-4"}]}},async created(){await this.sortWorldcupInfo(),this.isBusy=!1},computed:{...(0,o.rn)(c,["worldcups","win","totalwins"]),items(){return this.worldcups}},methods:{...(0,o.nv)(c,["sortWorldcupInfo"]),percentage(t){return parseInt(t)/parseInt(this.totalwins)*100},rowClass(t,e){if(t&&"row"===e)return t.id===this.win.id?"table-success":void 0}}},u=i,l=r(1001),p=(0,l.Z)(u,s,n,!1,null,null,null),a=p.exports},25459:function(t,e,r){var s={"./carousel1.png":59583,"./carousel2.png":92323,"./carousel3.png":33760,"./carousel4.png":5474,"./carousel5.png":30897,"./on_error.png":421,"./trip_logo.png":9152,"./worldcup1.png":33178,"./worldcup2.png":49500,"./worldcup3.png":55690,"./worldcup4.png":97088,"./worldcup5.png":84770,"./worldcup6.png":30667,"./worldcup7.png":57987,"./worldcup8.png":37611};function n(t){var e=o(t);return r(e)}function o(t){if(!r.o(s,t)){var e=new Error("Cannot find module '"+t+"'");throw e.code="MODULE_NOT_FOUND",e}return s[t]}n.keys=function(){return Object.keys(s)},n.resolve=o,t.exports=n,n.id=25459},5474:function(t,e,r){"use strict";t.exports=r.p+"img/carousel4.1a375c85.png"},33178:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup1.bf6977fe.png"},49500:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup2.b905a698.png"},55690:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup3.1c2d6516.png"},97088:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup4.9cb32efa.png"},84770:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup5.5104a063.png"},30667:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup6.1c103271.png"},57987:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup7.f06571da.png"},37611:function(t,e,r){"use strict";t.exports=r.p+"img/worldcup8.6aabc0d6.png"}}]);
//# sourceMappingURL=498.e63cd0f9.js.map