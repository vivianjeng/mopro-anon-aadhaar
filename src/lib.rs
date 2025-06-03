// Here we're calling a macro exported with Uniffi. This macro will
// write some functions and bind them to FFI type. These
// functions will invoke the `get_circom_wtns_fn` generated below.
mopro_ffi::app!();

// --- Circom Example of setting up multiplier2 circuit ---
use anyhow::Result;

// rust_witness::witness!(multiplier2);
witnesscalc_adapter::witness!(aadhaar_verifier);

mopro_ffi::set_circom_circuits! {
    ("circuit_final.zkey", mopro_ffi::witness::WitnessFn::WitnessCalc(aadhaar_verifier_witness))
}

// HALO2_TEMPLATE
