/*
 *  Do not modify this file; it is automatically 
 *  generated and any modifications will be overwritten.
 *
 * @(#) xdc-u12
 */
import java.util.*;
import org.mozilla.javascript.*;
import xdc.services.intern.xsr.*;
import xdc.services.spec.*;

public class ti_sdo_ce_examples_codecs_scale
{
    static final String VERS = "@(#) xdc-u12\n";

    static final Proto.Elm $$T_Bool = Proto.Elm.newBool();
    static final Proto.Elm $$T_Num = Proto.Elm.newNum();
    static final Proto.Elm $$T_Str = Proto.Elm.newStr();
    static final Proto.Elm $$T_Obj = Proto.Elm.newObj();

    static final Proto.Fxn $$T_Met = new Proto.Fxn(null, null, 0, -1, false);
    static final Proto.Map $$T_Map = new Proto.Map($$T_Obj);
    static final Proto.Arr $$T_Vec = new Proto.Arr($$T_Obj);

    static final XScriptO $$DEFAULT = Value.DEFAULT;
    static final Object $$UNDEF = Undefined.instance;

    static final Proto.Obj $$Package = (Proto.Obj)Global.get("$$Package");
    static final Proto.Obj $$Module = (Proto.Obj)Global.get("$$Module");
    static final Proto.Obj $$Instance = (Proto.Obj)Global.get("$$Instance");
    static final Proto.Obj $$Params = (Proto.Obj)Global.get("$$Params");

    static final Object $$objFldGet = Global.get("$$objFldGet");
    static final Object $$objFldSet = Global.get("$$objFldSet");
    static final Object $$proxyGet = Global.get("$$proxyGet");
    static final Object $$proxySet = Global.get("$$proxySet");
    static final Object $$delegGet = Global.get("$$delegGet");
    static final Object $$delegSet = Global.get("$$delegSet");

    Scriptable xdcO;
    Session ses;
    Value.Obj om;

    boolean isROV;
    boolean isCFG;

    Proto.Obj pkgP;
    Value.Obj pkgV;

    ArrayList<Object> imports = new ArrayList<Object>();
    ArrayList<Object> loggables = new ArrayList<Object>();
    ArrayList<Object> mcfgs = new ArrayList<Object>();
    ArrayList<Object> proxies = new ArrayList<Object>();
    ArrayList<Object> sizes = new ArrayList<Object>();
    ArrayList<Object> tdefs = new ArrayList<Object>();

    void $$IMPORTS()
    {
        Global.callFxn("loadPackage", xdcO, "xdc");
        Global.callFxn("loadPackage", xdcO, "xdc.corevers");
        Global.callFxn("loadPackage", xdcO, "ti.sdo.ce.examples.extensions.scale");
    }

    void $$OBJECTS()
    {
        pkgP = (Proto.Obj)om.bind("ti.sdo.ce.examples.codecs.scale.Package", new Proto.Obj());
        pkgV = (Value.Obj)om.bind("ti.sdo.ce.examples.codecs.scale", new Value.Obj("ti.sdo.ce.examples.codecs.scale", pkgP));
    }

    void SCALE_TI$$OBJECTS()
    {
        Proto.Obj po, spo;
        Value.Obj vo;

        po = (Proto.Obj)om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI.Module", new Proto.Obj());
        vo = (Value.Obj)om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI", new Value.Obj("ti.sdo.ce.examples.codecs.scale.SCALE_TI", po));
        pkgV.bind("SCALE_TI", vo);
        // decls 
    }

    void SCALE_TI$$CONSTS()
    {
        // module SCALE_TI
    }

    void SCALE_TI$$CREATES()
    {
        Proto.Fxn fxn;
        StringBuilder sb;

    }

    void SCALE_TI$$FUNCTIONS()
    {
        Proto.Fxn fxn;

    }

    void SCALE_TI$$SIZES()
    {
    }

    void SCALE_TI$$TYPES()
    {
        Scriptable cap;
        Proto.Obj po;
        Proto.Str ps;
        Proto.Typedef pt;
        Object fxn;

        cap = (Scriptable)Global.callFxn("loadCapsule", xdcO, "ti/sdo/ce/examples/codecs/scale/SCALE_TI.xs");
        om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$capsule", cap);
        po = (Proto.Obj)om.findStrict("ti.sdo.ce.examples.codecs.scale.SCALE_TI.Module", "ti.sdo.ce.examples.codecs.scale");
        po.init("ti.sdo.ce.examples.codecs.scale.SCALE_TI.Module", om.findStrict("ti.sdo.ce.examples.extensions.scale.ISCALE.Module", "ti.sdo.ce.examples.codecs.scale"));
                po.addFld("$hostonly", $$T_Num, 1, "r");
                po.addFld("ialgFxns", $$T_Str, "SCALE_TI_SCALE", "rh");
                fxn = Global.get(cap, "module$use");
                if (fxn != null) om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$module$use", true);
                if (fxn != null) po.addFxn("module$use", $$T_Met, fxn);
                fxn = Global.get(cap, "module$meta$init");
                if (fxn != null) om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$module$meta$init", true);
                if (fxn != null) po.addFxn("module$meta$init", $$T_Met, fxn);
                fxn = Global.get(cap, "module$validate");
                if (fxn != null) om.bind("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$module$validate", true);
                if (fxn != null) po.addFxn("module$validate", $$T_Met, fxn);
                fxn = Global.get(cap, "getCreationStackSize");
                if (fxn != null) po.addFxn("getCreationStackSize", (Proto.Fxn)om.findStrict("ti.sdo.ce.ICodec$$getCreationStackSize", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getDaramScratchSize");
                if (fxn != null) po.addFxn("getDaramScratchSize", (Proto.Fxn)om.findStrict("ti.sdo.ce.ICodec$$getDaramScratchSize", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getSaramScratchSize");
                if (fxn != null) po.addFxn("getSaramScratchSize", (Proto.Fxn)om.findStrict("ti.sdo.ce.ICodec$$getSaramScratchSize", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getStackSize");
                if (fxn != null) po.addFxn("getStackSize", (Proto.Fxn)om.findStrict("ti.sdo.ce.ICodec$$getStackSize", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getUUID");
                if (fxn != null) po.addFxn("getUUID", (Proto.Fxn)om.findStrict("ti.sdo.ce.ICodec$$getUUID", "ti.sdo.ce.examples.codecs.scale"), fxn);
    }

    void SCALE_TI$$ROV()
    {
    }

    void $$SINGLETONS()
    {
        pkgP.init("ti.sdo.ce.examples.codecs.scale.Package", (Proto.Obj)om.findStrict("xdc.IPackage.Module", "ti.sdo.ce.examples.codecs.scale"));
        Scriptable cap = (Scriptable)Global.callFxn("loadCapsule", xdcO, "ti/sdo/ce/examples/codecs/scale/package.xs");
        om.bind("xdc.IPackage$$capsule", cap);
        Object fxn;
                fxn = Global.get(cap, "init");
                if (fxn != null) pkgP.addFxn("init", (Proto.Fxn)om.findStrict("xdc.IPackage$$init", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "close");
                if (fxn != null) pkgP.addFxn("close", (Proto.Fxn)om.findStrict("xdc.IPackage$$close", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "validate");
                if (fxn != null) pkgP.addFxn("validate", (Proto.Fxn)om.findStrict("xdc.IPackage$$validate", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "exit");
                if (fxn != null) pkgP.addFxn("exit", (Proto.Fxn)om.findStrict("xdc.IPackage$$exit", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getLibs");
                if (fxn != null) pkgP.addFxn("getLibs", (Proto.Fxn)om.findStrict("xdc.IPackage$$getLibs", "ti.sdo.ce.examples.codecs.scale"), fxn);
                fxn = Global.get(cap, "getSects");
                if (fxn != null) pkgP.addFxn("getSects", (Proto.Fxn)om.findStrict("xdc.IPackage$$getSects", "ti.sdo.ce.examples.codecs.scale"), fxn);
        pkgP.bind("$capsule", cap);
        pkgV.init2(pkgP, "ti.sdo.ce.examples.codecs.scale", Value.DEFAULT, false);
        pkgV.bind("$name", "ti.sdo.ce.examples.codecs.scale");
        pkgV.bind("$category", "Package");
        pkgV.bind("$$qn", "ti.sdo.ce.examples.codecs.scale.");
        pkgV.bind("$vers", Global.newArray("1, 0, 0"));
        Value.Map atmap = (Value.Map)pkgV.getv("$attr");
        atmap.seal("length");
        imports.clear();
        pkgV.bind("$imports", imports);
        StringBuilder sb = new StringBuilder();
        sb.append("var pkg = xdc.om['ti.sdo.ce.examples.codecs.scale'];\n");
        sb.append("if (pkg.$vers.length >= 3) {\n");
            sb.append("pkg.$vers.push(Packages.xdc.services.global.Vers.getDate(xdc.csd() + '/..'));\n");
        sb.append("}\n");
        sb.append("pkg.build.libraries = [\n");
            sb.append("'lib/scale_ti.av4TCE',\n");
            sb.append("'lib/scale_ti.a86U',\n");
            sb.append("'lib/scale_ti.a674',\n");
            sb.append("'lib/scale_ti.a64P',\n");
            sb.append("'lib/scale_ti.a470uC',\n");
            sb.append("'lib/scale_ti.av5T',\n");
        sb.append("];\n");
        sb.append("pkg.build.libDesc = [\n");
            sb.append("['lib/scale_ti.av4TCE', {target: 'microsoft.targets.arm.WinCE'}],\n");
            sb.append("['lib/scale_ti.a86U', {target: 'gnu.targets.Linux86'}],\n");
            sb.append("['lib/scale_ti.a674', {target: 'ti.targets.C674'}],\n");
            sb.append("['lib/scale_ti.a64P', {target: 'ti.targets.C64P'}],\n");
            sb.append("['lib/scale_ti.a470uC', {target: 'gnu.targets.UCArm9'}],\n");
            sb.append("['lib/scale_ti.av5T', {target: 'gnu.targets.arm.GCArmv5T'}],\n");
        sb.append("];\n");
        sb.append("if('suffix' in xdc.om['xdc.IPackage$$LibDesc']) {\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.av4TCE'].suffix = 'v4TCE';\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.a86U'].suffix = '86U';\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.a674'].suffix = '674';\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.a64P'].suffix = '64P';\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.a470uC'].suffix = '470uC';\n");
            sb.append("pkg.build.libDesc['lib/scale_ti.av5T'].suffix = 'v5T';\n");
        sb.append("}\n");
        Global.eval(sb.toString());
    }

    void SCALE_TI$$SINGLETONS()
    {
        Proto.Obj po;
        Value.Obj vo;

        vo = (Value.Obj)om.findStrict("ti.sdo.ce.examples.codecs.scale.SCALE_TI", "ti.sdo.ce.examples.codecs.scale");
        po = (Proto.Obj)om.findStrict("ti.sdo.ce.examples.codecs.scale.SCALE_TI.Module", "ti.sdo.ce.examples.codecs.scale");
        vo.init2(po, "ti.sdo.ce.examples.codecs.scale.SCALE_TI", $$DEFAULT, false);
        vo.bind("Module", po);
        vo.bind("$category", "Module");
        vo.bind("$capsule", om.findStrict("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$capsule", "ti.sdo.ce.examples.codecs.scale"));
        vo.bind("$package", om.findStrict("ti.sdo.ce.examples.codecs.scale", "ti.sdo.ce.examples.codecs.scale"));
        tdefs.clear();
        proxies.clear();
        mcfgs.clear();
        vo.bind("$$tdefs", Global.newArray(tdefs.toArray()));
        vo.bind("$$proxies", Global.newArray(proxies.toArray()));
        vo.bind("$$mcfgs", Global.newArray(mcfgs.toArray()));
        ((Value.Arr)pkgV.getv("$modules")).add(vo);
        ((Value.Arr)om.findStrict("$modules", "ti.sdo.ce.examples.codecs.scale")).add(vo);
        vo.bind("$$instflag", 0);
        vo.bind("$$iobjflag", 1);
        vo.bind("$$sizeflag", 1);
        vo.bind("$$dlgflag", 0);
        vo.bind("$$iflag", 1);
        vo.bind("$$romcfgs", "|");
        Proto.Str ps = (Proto.Str)vo.find("Module_State");
        if (ps != null) vo.bind("$object", ps.newInstance());
        vo.bind("$$meta_iobj", om.has("ti.sdo.ce.examples.codecs.scale.SCALE_TI$$instance$static$init", null) ? 1 : 0);
        vo.bind("$$fxntab", Global.newArray());
        vo.bind("$$logEvtCfgs", Global.newArray());
        vo.bind("$$errorDescCfgs", Global.newArray());
        vo.bind("$$assertDescCfgs", Global.newArray());
        Value.Map atmap = (Value.Map)vo.getv("$attr");
        atmap.seal("length");
        pkgV.bind("SCALE_TI", vo);
        ((Value.Arr)pkgV.getv("$unitNames")).add("SCALE_TI");
    }

    void $$INITIALIZATION()
    {
        Value.Obj vo;

        if (isCFG) {
        }//isCFG
        Global.callFxn("module$meta$init", (Scriptable)om.findStrict("ti.sdo.ce.examples.codecs.scale.SCALE_TI", "ti.sdo.ce.examples.codecs.scale"));
        Global.callFxn("init", pkgV);
        ((Value.Obj)om.getv("ti.sdo.ce.examples.codecs.scale.SCALE_TI")).bless();
        ((Value.Arr)om.findStrict("$packages", "ti.sdo.ce.examples.codecs.scale")).add(pkgV);
    }

    public void exec( Scriptable xdcO, Session ses )
    {
        this.xdcO = xdcO;
        this.ses = ses;
        om = (Value.Obj)xdcO.get("om", null);

        Object o = om.geto("$name");
        String s = o instanceof String ? (String)o : null;
        isCFG = s != null && s.equals("cfg");
        isROV = s != null && s.equals("rov");

        $$IMPORTS();
        $$OBJECTS();
        SCALE_TI$$OBJECTS();
        SCALE_TI$$CONSTS();
        SCALE_TI$$CREATES();
        SCALE_TI$$FUNCTIONS();
        SCALE_TI$$SIZES();
        SCALE_TI$$TYPES();
        if (isROV) {
            SCALE_TI$$ROV();
        }//isROV
        $$SINGLETONS();
        SCALE_TI$$SINGLETONS();
        $$INITIALIZATION();
    }
}
