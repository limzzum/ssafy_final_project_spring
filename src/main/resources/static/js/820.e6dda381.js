(self["webpackChunktp_s09_s09_vue_enjoytrip_front"]=self["webpackChunktp_s09_s09_vue_enjoytrip_front"]||[]).push([[820],{93820:function(t,r,o){"use strict";o.r(r),o.d(r,{default:function(){return d}});var s=function(){var t=this,r=t._self._c;return r("div",[r("b-row",{staticClass:"m-3"},[r("b-col",{staticClass:"border border-primary rounded",staticStyle:{background:"rgba(131, 182, 255, 0.31)"}},[r("h2",{staticStyle:{"text-align":"center"},attrs:{id:"worldtitle"},domProps:{textContent:t._s("국내 관광지 월드컵 "+(1!=t.rounds?`${t.rounds<<1}강 (${t.round}/${t.rounds})`:"결승!!"))}})])],1),r("b-row",{staticClass:"m-3"},[r("b-col",{staticClass:"border border-primary rounded p-2",attrs:{cols:"5"},on:{click:function(r){return t.progress(0)}}},[r("b-img",{attrs:{src:o(25459)(`./${t.worldcups[0].img}`),fluid:""}}),r("h3",{staticClass:"text-center",domProps:{textContent:t._s(t.worldcups[0].content)}})],1),r("b-col",{staticClass:"align-self-center",attrs:{cols:"2"}},[r("h1",{staticClass:"text-center text-danger"},[t._v("VS")])]),r("b-col",{staticClass:"border border-primary rounded p-2",attrs:{cols:"5"},on:{click:function(r){return t.progress(1)}}},[r("b-img",{attrs:{src:o(25459)(`./${t.worldcups[1].img}`),fluid:""}}),r("h3",{staticClass:"text-center",domProps:{textContent:t._s(t.worldcups[1].content)}})],1)],1)],1)},n=[],e=(o(57658),o(90408));const c="worldcupStore";var u={name:"WorldcupProgress",components:{},data(){return{}},async created(){await this.getWorldcupInfo()},computed:{...(0,e.rn)(c,["worldcups","selected","rounds","round"])},methods:{...(0,e.nv)(c,["getWorldcupInfo","updateWorldcupResult","chooseWorldcup"]),async progress(t){1==this.rounds&&1==this.round?(this.updateWorldcupResult(this.worldcups[t]),this.$router.push({name:"result"})):this.chooseWorldcup(t)}}},p=u,i=o(1001),l=(0,i.Z)(p,s,n,!1,null,"05e7b62a",null),d=l.exports},25459:function(t,r,o){var s={"./carousel1.png":59583,"./carousel2.png":92323,"./carousel3.png":33760,"./carousel4.png":5474,"./carousel5.png":30897,"./on_error.png":421,"./trip_logo.png":9152,"./worldcup1.png":33178,"./worldcup2.png":49500,"./worldcup3.png":55690,"./worldcup4.png":97088,"./worldcup5.png":84770,"./worldcup6.png":30667,"./worldcup7.png":57987,"./worldcup8.png":37611};function n(t){var r=e(t);return o(r)}function e(t){if(!o.o(s,t)){var r=new Error("Cannot find module '"+t+"'");throw r.code="MODULE_NOT_FOUND",r}return s[t]}n.keys=function(){return Object.keys(s)},n.resolve=e,t.exports=n,n.id=25459},5474:function(t,r,o){"use strict";t.exports=o.p+"img/carousel4.1a375c85.png"},33178:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup1.bf6977fe.png"},49500:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup2.b905a698.png"},55690:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup3.1c2d6516.png"},97088:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup4.9cb32efa.png"},84770:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup5.5104a063.png"},30667:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup6.1c103271.png"},57987:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup7.f06571da.png"},37611:function(t,r,o){"use strict";t.exports=o.p+"img/worldcup8.6aabc0d6.png"}}]);
//# sourceMappingURL=820.e6dda381.js.map