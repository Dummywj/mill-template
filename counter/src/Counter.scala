import chisel3._
import circt.stage.ChiselStage

class Counter extends Module {
  val io = IO(new Bundle {
    val en  = Input(Bool())
    val out = Output(UInt(8.W))
  })

  val cnt = RegInit(0.U(8.W))

  when(io.en) {
    cnt := cnt + 1.U
  }

  io.out := cnt
}

object Elaborate extends App {
  ChiselStage.emitSystemVerilogFile(
    new Counter,
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
  )
}
