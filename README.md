# Journey Into the Light
Experience an immersive RPG-style adventure mod that doesn't stray too far from Vanilla's roots.


Journey Into The Light (or "Journey" for short), formerly known as Essence of the Gods, is a large exploration mod that not only expands the vanilla content added - but also adds 7 new dimensions, over one hundred mobs, strong weapons and armor, and much more.

## Contributing

As per the Github Terms of Service, you grant us the right to use your contribution under the same license as this
project.

In addition, we request that you give us the right to change the license in the future.

Journey Into the Light uses a specialized mapping set which adds the readable parameter names to the functions.
See [Mappificator Project](https://github.com/alcatrazEscapee/Mappificator) in order to install that. You need to
generate the mappings, using the command below, while you are in the root folder of that project.

```
py src/mappificator.py -p -v 1 --mc-version 1.18.1 --providers yarn parchment --yarn-version 12 --parchment-version 2021.12.19-1.18.1
```