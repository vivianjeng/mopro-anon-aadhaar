package expo.modules.mopro

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.io.File
import uniffi.mopro.ProofCalldata
import uniffi.mopro.generateCircomProof
import uniffi.mopro.toEthereumInputs
import uniffi.mopro.toEthereumProof

fun convertType(proof: ProofCalldata): ExpoProof {
  var a = ExpoG1(proof.a.x, proof.a.y)
  var b = ExpoG2(proof.b.x, proof.b.y)
  var c = ExpoG1(proof.c.x, proof.c.y)
  var output = ExpoProof(a, b, c)
  return output
}

fun generateProof(zkeyPath: String, circuitInputs: Map<String, List<String>>): Result {
  val file = File(zkeyPath)
  val res = generateCircomProof(file.absolutePath, circuitInputs)
  val proof = toEthereumProof(res.proof)
  val inputs = toEthereumInputs(res.inputs)
  val result = Result(convertType(proof), inputs)
  return result
}

class MoproModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a
    // string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for
    // clarity.
    // The module will be accessible from `requireNativeModule('Mopro')` in JavaScript.
    Name("Mopro")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a
    // dictionary.
    Constants("PI" to Math.PI)

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") { "Hello world! 👋" }

    Function("generateCircomProof") { zkeyPath: String, circuitInputs: Map<String, List<String>> ->
      generateProof(zkeyPath, circuitInputs)
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { value: String ->
      // Send an event to JavaScript.
      sendEvent("onChange", mapOf("value" to value))
    }

    // Enables the module to be used as a native view. Definition components that are accepted as
    // part of
    // the view definition: Prop, Events.
    View(MoproView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: MoproView, prop: String -> println(prop) }
    }
  }
}
