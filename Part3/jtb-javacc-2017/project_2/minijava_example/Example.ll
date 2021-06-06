
@.Example_vtable = global [0 x i8*] []
@.Fac_vtable = global [2 x i8*] [i8* bitcast (i32 (i8*,i32)* @Fac.ComputeFac to i8*), i8* bitcast (i32 (i8*)* @Fac.testfun to i8*)]
@.B_vtable = global [3 x i8*] [i8* bitcast (i32 (i8*,i32)* @B.ComputeFac to i8*),i8* bitcast (i32 (i8*)* @Fac.testfun to i8*), i8* bitcast (i1 (i8*)* @B.foobar to i8*)]
@.B_vtable = global [3 x i8*] [i8* bitcast (i32 (i8*,i32)* @B.ComputeFac to i8*), i8* bitcast (i32 (i8*)* @Fac.testfun to i8*), i8* bitcast (i1 (i8*)* @B.foobar to i8*)]
