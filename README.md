# low-disc

A Clojure library of [low discrepancy
sequence](https://en.wikipedia.org/wiki/Low-discrepancy_sequence) algorithms.

The following sequences are implemented:

* Van der Corput sequence
* Halton sequence
* Hammersley sequence
* Uniform random sequence (for comparison)

## Illustrations

### Uniform

![](/img/uniform.png)

### Halton Sequence

| Dimensions  | Unscrambled | Scrambled |
|----------------------|:-----------:|:---------:|
| 1 and 2   | ![](/img/halton-unscr.png) | ![](/img/halton.png) |
| 15 and 16 | ![](/img/halton-unscr-high.png) | ![](/img/halton-high.png) |

### Hammersley Sequence


| Dimensions  | Unscrambled | Scrambled |
|----------------------|:-----------:|:---------:|
| 1 and 2   | ![](/img/hammersley-unscr.png) | ![](/img/hammersley.png) |
| 15 and 16 | ![](/img/hammersley-unscr-high.png) | ![](/img/hammersley-high.png) |


## Usage

All sequence functions return lazy sequences of points. Points are `n`-dimensional vectors whose
coordinates are each between 0 and 1. The only exception is the one dimensional van der Corput
sequence whose elements are scalars.

With the exception of sequences that are finite by definition, all sequence functions return
lazy inifinite sequences by default. If the optional parameter `l` is given, only the first `l`
elements are returned.

Many sequences come in two variants: scrambled and unscrambled ones. The scrambled one is usually
the default, whereas the unscrambled one is denoted by the suffix `unscr` (e.g. `halton-2d` vs.
`halton-unscr-2d`).

### Van der Corput Sequence

The van der Corput sequence is parametrized by a basis `b` which should be a prime number. It can be
scrambled using a permutation `p`, specified in the form of a vector: It must contain the first `b`
integers starting with `0` in an order which defines the permutation (e.g. for basis `3` `[0 2 1]`)

```Clojure
(vdc p b)
(vdc p b l)

(vdc-unscr b)
(vdc-unscr b l)
```

```Clojure
(vdc-unscr 3 5)  ;;=> (1/3 2/3 1/9 4/9 7/9)
```

### Halton Sequence

The Halton sequence is a generalization of the van der Corput sequence to the n-dimensional case.
Consequently, it is parametrized by `n` prime bases which should be distinct. It can be scrambled
by `n` permutations. 

Permutations can be specified manually using the `halton` function. Alternatively, the `halton-nd`
functions provide defaults for the first 16 dimensions taken from the literature.

```Clojure
(halton ps bs)
(halton ps bs l)

(halton-unscr bs)
(halton-unscr bs l)

(halton-nd n)
(halton-nd n l)

(halton-unscr-nd n)
(halton-unscr-nd n l)

(halton-1d)
(halton-1d l)
...
(halton-16d)
(halton-16d l)

(halton-unscr-1d)
(halton-unscr-1d l)
...
(halton-unscr-16d)
(halton-unscr-16d l)
```

```Clojure
(halton-2d 3)  ;;=> ([1/2 2/3] [1/4 1/3] [3/4 2/9])
```

### Hammersley sequence

The Hammersley sequence has a defined size `l`. There are both scrambled and unscrambled versions.
The former use the same defaults as their Halton equivalents.

```Clojure
(hammersley-nd n l)

(hammersley-unscr-nd n l)

(hammersley-1d l)
...
(hammersley-16d l)

(hammersley-unscr-1d l)
...
(hammersley-unscr-16d l)
```

```Clojure
(hammersley-2d 3)  ;;=> ([1/2 0] [1/4 1/3] [3/4 2/3])
```

### Random sequence

For comparison, random sequences are provided. They consist of points chosen uniformly, at random,
and independently of each other inside the unit plane (cube, ...).

```Clojure
(uniform-nd n)
(uniform-nd n l)

(uniform-1d)
(uniform-1d l)
...
(uniform-16d)
(uniform-16d l)
```

```Clojure
(uniform-2d 3) ;;=> ([0.5270699200579589 0.47777654304867256]
               ;;    [0.8942239443816675 0.032992693354966796]
               ;;    [0.7448379865121517 0.4345372587047025])
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
