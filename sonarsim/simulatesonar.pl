%====================================================================================
% simulatesonar description   
%====================================================================================
event( distanceChange, distance(D) ).
%====================================================================================
context(ctxsimulate, "localhost",  "TCP", "8080").
 qactor( simulate, ctxsimulate, "it.unibo.simulate.Simulate").
 static(simulate).
  qactor( printval, ctxsimulate, "it.unibo.printval.Printval").
 static(printval).
