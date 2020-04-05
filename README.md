# low-disc

A Clojure library of [low discrepancy
sequence](https://en.wikipedia.org/wiki/Low-discrepancy_sequence) algorithms.

The following algorithms are implemented:

* Van der Corput sequence (scrambled and unscrambled)
* Halton sequence (scrambled and unscrambled)

## Usage

### Van der Corput Sequence

```Clojure
(take 4 (low-disc.vdc/vdc 2))         ;;=> (1/2 1/4 3/4 1/8)
```

### Halton Sequence

```Clojure
(take 4 (low-disc.halton/halton-2d))  ;;=> ([1/2 2/3] [1/4 1/3] [3/4 2/9] [1/8 8/9])
```

## License

Copyright © 2020 Jannik Luhn

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
