function IG(){}
function Q_b(a,b){MB(a.a,b)}
function Fsb(a,b){this.b=a;this.a=b}
function Isb(a,b){this.b=a;this.a=b}
function fH(a){return t3(SG,a)}
function HG(){HG=Rpc;GG=new IG}
function xsb(a,b){MTb(b,d_c+a.dg()+rwc+a.eg())}
function b0b(){Y_b();a0b.call(this,rr($doc,n_c),o_c)}
function e9b(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;return c.text.length}catch(a){return 0}}
function d9b(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;return -c.move(p_c,-65535)}catch(a){return 0}}
function wsb(a,b){var c,d;c=new pYb;c.e[TGc]=4;mYb(c,a);if(b){d=new QTb(c_c);kj(a,new Fsb(a,d),(dy(),dy(),cy));kj(a,new Isb(a,d),(tx(),tx(),sx));mYb(c,d)}return c}
function g9b(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;var d=c.text.length;var e=0;var f=c.duplicate();f.moveEnd(p_c,-1);var g=f.text.length;while(g==d&&f.parentElement()==b&&c.compareEndPoints(r_c,f)<=0){e+=2;f.moveEnd(p_c,-1);g=f.text.length}return d+e}catch(a){return 0}}
function f9b(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;var d=c.duplicate();d.moveToElementText(b);d.setEndPoint(q_c,c);var e=d.text.length;var f=0;var g=d.duplicate();g.moveEnd(p_c,-1);var i=g.text.length;while(i==e&&g.parentElement()==b){f+=2;g.moveEnd(p_c,-1);i=g.text.length}return e+f}catch(a){return 0}}
var h_c='<b>\u5E38\u89C4\u6587\u672C\u6846:<\/b>',k_c='<br><br><b>\u5BC6\u7801\u6587\u672C\u6846:<\/b>',m_c='<br><br><b>\u6587\u672C\u533A\u57DF:<\/b>',v_c='AnyRtlDirectionEstimator',s_c='CwBasicText$2',t_c='CwBasicText$3',q_c='EndToStart',u_c='PasswordTextBox',r_c='StartToEnd',p_c='character',i_c='cwBasicText-password',j_c='cwBasicText-password-disabled',l_c='cwBasicText-textarea',e_c='cwBasicText-textbox',f_c='cwBasicText-textbox-disabled',o_c='gwt-PasswordTextBox',n_c='password',g_c='\u53EA\u8BFB',d_c='\u5DF2\u9009\u62E9: ',c_c='\u5DF2\u9009\u62E9: 0, 0';d2(368,369,{},IG);_.xd=function JG(a){return fH((_G(),a))?(kE(),jE):(kE(),iE)};var GG;d2(756,1,Mqc);_.mc=function Dsb(){var a,b,c,d,e,f;k5(this.a,(a=new E7b,a.e[TGc]=5,b=new __b,g7b(b.cb,tsc,e_c),Q_b(b,(HG(),HG(),GG)),c=new __b,g7b(c.cb,tsc,f_c),c.cb[kWc]=g_c,LB(c.a),c.cb[ZTc]=true,B7b(a,new VTb(h_c)),B7b(a,wsb(b,true)),B7b(a,wsb(c,false)),d=new b0b,g7b(d.cb,tsc,i_c),e=new b0b,g7b(e.cb,tsc,j_c),e.cb[kWc]=g_c,LB(e.a),e.cb[ZTc]=true,B7b(a,new VTb(k_c)),B7b(a,wsb(d,true)),B7b(a,wsb(e,false)),f=new p5b,g7b(f.cb,tsc,l_c),f.cb.rows=5,B7b(a,new VTb(m_c)),B7b(a,wsb(f,true)),a))};d2(757,1,tqc,Fsb);_.Gc=function Gsb(a){xsb(this.b,this.a)};_.a=null;_.b=null;d2(758,1,Jqc,Isb);_.Ec=function Jsb(a){xsb(this.b,this.a)};_.a=null;_.b=null;d2(1100,983,$pc);_.dg=function T_b(){return d9b(this.cb)};_.eg=function U_b(){return e9b(this.cb)};d2(1097,1098,$pc,b0b);d2(1155,1099,$pc);_.dg=function q5b(){return f9b(this.cb)};_.eg=function r5b(){return g9b(this.cb)};var qT=afc(RKc,s_c,757),rT=afc(RKc,t_c,758),kY=afc(SJc,u_c,1097),JN=afc(gQc,v_c,368);yrc(wn)(18);