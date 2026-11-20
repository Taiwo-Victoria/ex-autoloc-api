# AutoLoc — Fetch and cascade strategy

| Association            | Mapping                                                | Fetch | Cascade | Justification                                                                      |
|------------------------|--------------------------------------------------------|-------|---------|------------------------------------------------------------------------------------| 
| Contract → Payment     | @ManyToOne / @OneToMany; Payment owns FK               | LAZY  | ALL     | Payments depend on their contract and are removed with it.                         |
| Agence → Vehicle       | @OneToMany / @ManyToOne; Vehicle owns FK               | LAZY  | None    | A vehicle survives the removal of an agency, so deletion is not cascaded.          |
| Agence → Employee      | @OneToMany / @ManyToOne; Employee owns FK              | LAZY  | None    | Employees have their own lifeline so removing an agency must not delete them.      |
| Vehicle ↔ Equipment    | @ManyToMany; Vehicle owns join table vehicle_equipment | LAZY  | None    | Equipment is shared between vehicles; the Set avoids duplicates in the join table. |
| Client → Reservation   | @OneToMany / @ManyToOne; Reservation owns FK           | LAZY  | PERSIST | New reservations are saved with their client, but are not deleted with it.         |
| Reservation → Vehicle  | @ManyToOne; Reservation owns FK                        | LAZY  | None    | A reservation only refers to a vehicle; the vehicle has its own lifeline.          |
| Reservation ↔ Contract | @OneToOne; Contract owns FK                            | LAZY  | ALL     | The contract only exists for its reservation and follows all its operations.       |
| Vehicle → Maintenance  | @OneToMany / @ManyToOne; Maintenance owns FK           | LAZY  | PERSIST | New maintenance records are saved with their vehicle, but kept in the history.     |

All associations are LAZY to avoid loading chains of related objects.